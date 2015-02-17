package log.reactive;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import log.reactive.RxLogApplication.Ordering;
import nl.tudelft.reactiveui.javafx.observable.FXObservable;

public class RxLogApplication extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = new FXMLLoader().load(new FileInputStream("src/main/fxml/log.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		
		List<String> values = ((ListView<String>) scene.lookup("#logList")).getItems();
		
		FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION)
		.map((event) -> (TextInputControl) event.getSource())
		.filter((input) -> !input.getText().isEmpty())
		.forEach((input) -> {
			values.add(input.getText());
			input.setText("");
		});
		
		FXObservable.node(scene.lookup("#sortAZ"), ActionEvent.ACTION)
		.map((x) -> Ordering.NORMAL)
		.mergeWith(FXObservable.node(scene.lookup("#sortZA"), ActionEvent.ACTION).map((x) -> Ordering.REVERSE))
		.forEach((ordering) -> {
			Comparator<String> comparator = null;
			switch(ordering){
			case NORMAL:
				comparator = (x, y) -> x.compareTo(y);
				break;
			case REVERSE:
				comparator = (x, y) -> y.compareTo(x);
				break;
			}
			Collections.sort(values, comparator);
		});
		stage.show();
	}
	
	enum Ordering{
		NORMAL,
		REVERSE
		;
	}
}
