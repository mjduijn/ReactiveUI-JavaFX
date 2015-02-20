package log;

import nl.tudelft.reactiveui.javafx.observable.ObservableVariable;



public class LogViewModel {
	private ObservableVariable<String> error;
	
	public LogViewModel() {
		error = new ObservableVariable<String>("");
	}
	
	public ObservableVariable<String> getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error.setValue(error);
	}
	
	
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
