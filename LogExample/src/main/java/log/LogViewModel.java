package log;

import javafx.beans.property.SimpleStringProperty;
import nl.tudelft.reactiveui.javafx.observable.ObservableVariable;



public class LogViewModel {
	private ObservableVariable<String> error;
	private javafx.beans.property.StringProperty error2;
	
	public LogViewModel() {
		error = new ObservableVariable<String>("");
		error2 = new SimpleStringProperty();
		
	}

	public ObservableVariable<String> getError() {
		return error;
	}

	public javafx.beans.property.StringProperty  getError2() {
		return error2;
	}
	
	public void setError(String error) {
		this.error.setValue(error);
		this.error2.setValue(error);
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
