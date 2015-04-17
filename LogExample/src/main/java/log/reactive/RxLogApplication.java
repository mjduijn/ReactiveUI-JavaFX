package log.reactive;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import log.LogViewModel;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.FXObserver;
import rx.Observable;
import rx.functions.Action1;

@SuppressWarnings("unchecked")
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
		
		
		ListView<String> logList = ((ListView<String>) scene.lookup("#logList"));
		LogViewModel model = new LogViewModel(logList.getItems());
		model.list.addAll(Arrays.asList(new String[]{"bla", "bli", "blo"}));
		
		
		Label errorLabel = (Label) scene.lookup("#logError");
		model.error.observable.subscribe(FXObserver.label.text(errorLabel));
		model.error.observable.map(s -> s.isEmpty()).subscribe(FXObserver.node.enabled(scene.lookup("#addLog")));
		
		TextField logField = (TextField) scene.lookup("#logField");
		FXObservable.node(logField, KeyEvent.KEY_RELEASED).forEach(event -> model.content.setValue(((TextInputControl) event.getSource()).getText()));		
		model.content.observable.filter(x -> x.isEmpty()).forEach(FXObserver.textField.setText(logField));
		
		
		model.content.observable.forEach(input -> {
			if(input.isEmpty()){
				model.setError(String.format("Value may not be empty"));
			} else if(model.list.contains(input)){
				model.setError(String.format("List already contains %s", input));
			} else{
				model.setError("");
			}
		});
		
		Action1<TextInputControl> add = (input) -> {
			if(!model.list.contains(input.getText())) {
				model.list.add(input.getText());
				model.content.setValue("");
			}
		};
		
		Observable.merge(
				FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION),
				FXObservable.node(scene.lookup("#addLog"), ActionEvent.ACTION)
		)
		.map((o) -> (TextInputControl) scene.lookup("#logField"))
		.filter((input) -> !input.getText().isEmpty())
		.subscribe(add);
		
		FXObservable.node(scene.lookup("#sortAZ"), ActionEvent.ACTION)
		.map((event) -> (Comparator<String>) (x, y) -> x.compareTo(y))
		.mergeWith(FXObservable.node(scene.lookup("#sortZA"), ActionEvent.ACTION).map((event) -> (Comparator<String>) (x, y) -> y.compareTo(x)))
		.forEach((comparator) -> {
			Collections.sort(model.list, comparator);
		});
		stage.show();
	}
}
