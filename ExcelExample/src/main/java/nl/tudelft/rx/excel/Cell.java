package nl.tudelft.rx.excel;

import nl.tudelft.rxfx.observable.ObservableVariable;

public class Cell {
	public final ObservableVariable<String> result;
	private String formula;
	
	public static final Cell EMPTY = new Cell(new ObservableVariable<String>(""), "");
	
	public Cell(ObservableVariable<String> result, String formula) {
		this.result = result;
		this.formula = formula;
	}
	
	public String getResult() {
		return result.getValue();
	}
	
	public String getFormula(){
		return formula;
	}
	
	public void setResult(String result){
		this.result.setValue(result);
	}
	
	public void setFormula(String formula){
		this.formula = formula;
	}
}
