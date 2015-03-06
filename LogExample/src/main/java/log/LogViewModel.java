package log;

import java.util.List;

import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;



public class LogViewModel {
	private ObservableVariable<String> error;
	private ObservableListVariable<String> list;
	
	
	public LogViewModel(List<String> list) {
		error = new ObservableVariable<String>("");
		this.list = new ObservableListVariable<String>(list);
	}

	public ObservableVariable<String> getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error.setValue(error);
	}
	
	public ObservableListVariable<String> getList() {
		return list;
	}
	
//		Mutable variable try out
//	private ObservableMutableVariable<javafx.beans.property.BooleanProperty> tester;
//	
//	public void setTester(boolean b) {
//		this.tester.setValue(b);
//		BooleanProperty a;
//		a.set
//	}
	
	
//	private ObservableVariable<List<String>> logMessages;
//	
//	
//	public void setaString(String s) {
//		aString.setValue(s);
//	}
//	
//	public String getaString() {
//		return aString.getValue();
//	}
//	
//	
//
//	public LogModel(List<String> logMessages) {
//	}
//	
//	public List<String> getLogMessages() {
//		return logMessages.getValue();
//	}
	
	
	
}
