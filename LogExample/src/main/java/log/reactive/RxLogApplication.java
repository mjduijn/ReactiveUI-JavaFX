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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import log.LogViewModel;
import nl.tudelft.reactiveui.javafx.observable.FXObservable;
import nl.tudelft.reactiveui.javafx.observable.FXObserver;
import rx.Observable;
import rx.functions.Action1;

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
		
		LogViewModel model = new LogViewModel();
		model.getError().subscribe(FXObserver.label.text(errorLabel));
		model.getError().map(s -> s.isEmpty()).subscribe(FXObserver.node.enabled(scene.lookup("#addLog")));
		
		FXObservable.javaObservable(model.getError2())
		.subscribe(System.out::println);
		
		FXObservable.node(scene.lookup("#logField"), KeyEvent.KEY_RELEASED)
		.map(event -> ((TextInputControl) event.getSource()).getText())
		.forEach(input -> {
			System.out.println(input);
			if(input.isEmpty()){
				model.setError(String.format("Value may not be empty"));
			} else if(values.contains(input)){
				model.setError(String.format("List already contains %s", input));
			} else{
				model.setError("");
			}
		});
		
		Action1<TextInputControl> add = (input) -> {
			if(!values.contains(input.getText())) {
				values.add(input.getText());
				input.clear();
			}
		};
		
		Observable.combineLatest(
				FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION),
				FXObservable.node(scene.lookup("#addLog"), ActionEvent.ACTION), 
				(o1, o2) -> o1
		)
		.map((event) -> (TextInputControl) event.getSource())
		.filter((input) -> !input.getText().isEmpty())
		.subscribe(add);
		
		scene.lookup("#logField").fireEvent(new ActionEvent());
		
		FXObservable.node(scene.lookup("#sortAZ"), ActionEvent.ACTION)
		.map((event) -> (Comparator<String>) (x, y) -> x.compareTo(y))
		.mergeWith(FXObservable.node(scene.lookup("#sortZA"), ActionEvent.ACTION).map((event) -> (Comparator<String>) (x, y) -> y.compareTo(x)))
		.forEach((comparator) -> {
			Collections.sort(values, comparator);
		});
		stage.show();
	}
}
