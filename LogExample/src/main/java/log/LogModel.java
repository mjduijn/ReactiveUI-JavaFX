package log;

import java.util.List;

public class LogModel {
	private List<String> logMessages;

	public LogModel(List<String> logMessages) {
		this.logMessages = logMessages;
	}
	
	public List<String> getLogMessages() {
		return logMessages;
		
	}
}
