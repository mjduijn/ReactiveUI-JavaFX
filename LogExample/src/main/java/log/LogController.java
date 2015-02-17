package log;

import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LogController {
	@FXML TextField logField;
	@FXML ListView<String> logList;
	
	@FXML
	public void insert(ActionEvent event){
		logList.getItems().add(0, logField.getText());
		logField.setText(null);
	}
	
	@FXML
	public void sortAZ(ActionEvent event){
		System.out.println(event.getEventType());
		Collections.sort(logList.getItems(), (x,y) -> x.compareTo(y));
	}
	
	@FXML
	public void sortZA(ActionEvent event){
		Collections.sort(logList.getItems(), (x,y) -> -x.compareTo(y));
	}
}
