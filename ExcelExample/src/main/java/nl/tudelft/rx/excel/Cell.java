package nl.tudelft.rx.excel;

import java.util.LinkedList;
import java.util.List;

import nl.tudelft.rx.excel.parser.ExcelCellRefResolverVisitor;
import nl.tudelft.rx.excel.parser.ExcelInterpreter;
import nl.tudelft.rx.excel.parser.ExcelParser;
import nl.tudelft.rx.excel.parser.Expression;
import nl.tudelft.rx.excel.parser.ExpressionWithCellRefs;
import nl.tudelft.rxfx.observable.ObservableVariable;

import org.javafp.parsecj.Reply;

import rx.Subscription;
import rx.functions.Func2;

public class Cell {
	public final ObservableVariable<String> result;
	public final ObservableVariable<String> formula;
	
	private int row;
	private String column;
	
	private List<Subscription> subscriptions;
	private ExcelParser parser;
	
	private Func2<Integer, String, Cell> cellSearcher;
	
	private static final Cell empty = empty();
	public static final Cell empty() { 
		return new Cell(-1, "", (row, column) -> empty);
	}
	
	public Cell(int row, String column, Func2<Integer, String, Cell> cellSearcher) {
		this(new ObservableVariable<>(""), new ObservableVariable<>(""), row, column, cellSearcher);
	}
	
	public Cell(ObservableVariable<String> result, ObservableVariable<String> formula, int row, String column, Func2<Integer, String, Cell> cellSearcher) {
		this.result = result;
		this.formula = formula;
		this.parser = new ExcelParser();
		this.cellSearcher = cellSearcher;
		this.subscriptions = new LinkedList<Subscription>();
		
		formula.observable.subscribe(next -> {
			
			System.out.println("new formula: " + next);
			subscriptions.forEach(s -> s.unsubscribe());
			subscriptions.clear();
			
			System.out.println("the formula to parse is: " + next);
			Reply<Character, Expression> parsed = parser.parse(next);
			
			if(parsed.isOk()){
				final Expression exp = safeGetResult(parsed);
				ExpressionWithCellRefs expWithCellRefs = exp.visit(new ExcelCellRefResolverVisitor((r, c) -> Integer.parseInt(cellSearcher.call(r, c).getResult())), new LinkedList<>());
				System.out.println(expWithCellRefs.cellRefs);
				expWithCellRefs.cellRefs.forEach(cellRef -> subscriptions.add(cellSearcher.call(cellRef.row, cellRef.column).result.observable.subscribe(newResult -> {
					updateExpression(exp);
				})));
				Integer res = expWithCellRefs.exp.visit(new ExcelInterpreter(), null);
				result.setValue("" + res);
			} else{
				result.setValue("Error");
			}
		});
	}
	
	private void updateExpression(Expression exp){
		ExpressionWithCellRefs expWithCellRefs = exp.visit(new ExcelCellRefResolverVisitor((r, c) -> Integer.parseInt(cellSearcher.call(r, c).getResult())), new LinkedList<>());
		Integer res = expWithCellRefs.exp.visit(new ExcelInterpreter(), null);
		result.setValue("" + res);
	}
	
	private Expression safeGetResult(Reply<Character, Expression> reply){
		try {
			return reply.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getResult() {
		return result.getValue();
	}
	
	public String getFormula(){
		return formula.getValue();
	}
	
	public void setResult(String result){
		this.result.setValue(result);
	}
	
	public void setFormula(String formula){
		this.formula.setValue(formula);
	}

	@Override
	public String toString() {
		return "Cell [result=" + result + ", formula=" + formula + ", row="
				+ row + ", column=" + column + ", subscriptions="
				+ subscriptions + ", parser=" + parser + ", cellSearcher="
				+ cellSearcher + "]";
	}
	
	
}
