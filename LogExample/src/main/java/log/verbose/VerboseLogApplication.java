package log.verbose;

import java.util.Collections;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VerboseLogApplication extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setPadding(new Insets(25, 10, 25, 10));
		
		ListView<String> logList = new ListView<String>();
		GridPane.setRowIndex(logList, 1);
		GridPane.setColumnSpan(logList, 2);
		
		TextField logField = new TextField();
		GridPane.setRowIndex(logField, 0);
		GridPane.setColumnSpan(logField, 2);
		logField.setOnAction((event) -> {
			logList.getItems().add(logField.getText());
			logField.setText(null);
		});
		
		Button sortAZ = new Button("Sort Ascending");
		GridPane.setRowIndex(sortAZ, 2);
		GridPane.setColumnIndex(sortAZ, 0);
		sortAZ.setOnAction((event) -> Collections.sort(logList.getItems(), (x, y) -> x.compareTo(y)));
		
		Button sortZA = new Button("Sort Ascending");
		GridPane.setRowIndex(sortZA, 2);
		GridPane.setColumnIndex(sortZA, 1);
		sortZA.setOnAction((event) -> Collections.sort(logList.getItems(), (x, y) -> -x.compareTo(y)));
		
		pane.getChildren().add(logField);
		pane.getChildren().add(logList);
		pane.getChildren().add(sortAZ);
		pane.getChildren().add(sortZA);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		stage.show();
	}
}
