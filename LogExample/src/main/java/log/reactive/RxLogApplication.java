package log.reactive;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.exceptions.OnErrorThrowable.OnNextValue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import nl.tudelft.reactiveui.javafx.observable.FXObservable;
import nl.tudelft.reactiveui.javafx.scheduler.FXScheduler;

public class RxLogApplication extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = new FXMLLoader().load(new FileInputStream("src/main/fxml/log.fxml"));
		stage.setTitle("Reactive Log");
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		
		List<String> values = ((ListView<String>) scene.lookup("#logList")).getItems();
		Label errorLabel = (Label) scene.lookup("#logError");
		
		FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION)
		.map((event) -> (TextInputControl) event.getSource())
		.filter((input) -> !input.getText().isEmpty())
		.observeOn(FXScheduler.getInstance())
		.forEach((input) -> {
			if(values.contains(input.getText())){
				errorLabel.setText(String.format("List already contains value \'%s\'", input.getText()));
			} else{
				values.add(input.getText());
				input.clear();
				errorLabel.setText("");
			}
		});
		
		
		FXObservable.node(scene.lookup("#sortAZ"), ActionEvent.ACTION)
		.map((event) -> (Comparator<String>) (x, y) -> x.compareTo(y))
		.mergeWith(FXObservable.node(scene.lookup("#sortZA"), ActionEvent.ACTION).map((event) -> (Comparator<String>) (x, y) -> y.compareTo(x)))
		.forEach((comparator) -> {
			Collections.sort(values, comparator);
		});
		stage.show();
	}
}
