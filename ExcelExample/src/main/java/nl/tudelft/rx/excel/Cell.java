package nl.tudelft.rx.excel;

import nl.tudelft.reactiveui.javafx.observable.ObservableVariable;

public class Cell {
	private ObservableVariable<String> result;
	private ObservableVariable<String> value;
	
	public ObservableVariable<String> getResultObservable() {
		return result;
	}
	public ObservableVariable<String> getValueObservable() {
		return value;
	}
	
	public String getResult() {
		return result.getValue();
	}
	
	public String getValue(){
		return value.getValue();
	}
	
	public void setResult(String result){
		this.result.setValue(result);
	}
	
	public void setValue(String value){
		this.value.setValue(value);
	}
}
