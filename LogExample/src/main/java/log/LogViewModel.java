package log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;
import rx.Subscription;



public class LogViewModel {
	public final ObservableVariable<String> content;
	public final ObservableVariable<String> error;
	public final ObservableListVariable<String> list;
	
	public LogViewModel(List<String> list) {
		this.content = new ObservableVariable<String>("");
		this.error = new ObservableVariable<String>("");
		this.list = new ObservableListVariable<String>(list);
	}
	
	public void setError(String error) {
		this.error.setValue(error);
	}
	
	public void setContent(String content){
		this.content.setValue(content);
	}
	
}