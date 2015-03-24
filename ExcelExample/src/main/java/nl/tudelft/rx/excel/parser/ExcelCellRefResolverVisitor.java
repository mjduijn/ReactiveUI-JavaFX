package nl.tudelft.rx.excel.parser;

import java.util.List;

import rx.functions.Func2;

public class ExcelCellRefResolverVisitor implements ExcelVisitor<ExpressionWithCellRefs, List<CellRefExpression>>{

	private Func2<Integer, String, Integer> cellFinder;
	
	public ExcelCellRefResolverVisitor(Func2<Integer, String, Integer> cellFinder) {
		this.cellFinder = cellFinder;
	}

	@Override
	public ExpressionWithCellRefs visitIntegerExpression(IntegerExpression e,
			List<CellRefExpression> obj) {
		return new ExpressionWithCellRefs(e, obj);
	}

	@Override
	public ExpressionWithCellRefs visitBinaryExpression(BinaryExpression e,
			List<CellRefExpression> obj) {
		ExpressionWithCellRefs e1 = e.e1.visit(this, obj);
		ExpressionWithCellRefs e2 = e.e2.visit(this, obj);
		return new ExpressionWithCellRefs(new BinaryExpression(e1.exp, e.op, e2.exp), obj);
	}

	@Override
	public ExpressionWithCellRefs visitCellRef(CellRefExpression e,
			List<CellRefExpression> obj) {
		obj.add(e);
		return new ExpressionWithCellRefs(new IntegerExpression(cellFinder.call(e.row, e.column)), obj);
	}



}
