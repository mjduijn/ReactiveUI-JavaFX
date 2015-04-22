package log.reactive;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
import nl.tudelft.rxfx.observable.FXObservers;
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
		model.error.observable.subscribe(FXObservers.label.text(errorLabel)); //Bind error variable to error label in UI
		
		model.error.observable
			.map(s -> s.isEmpty())
			.subscribe(FXObservers.node.enabled(scene.lookup("#addLog"))); //Enable/disable add button depending on if there is an error
		
		TextField logField = (TextField) scene.lookup("#logField");
		
		FXObservable.node(logField, KeyEvent.KEY_RELEASED)
			.forEach( event -> 
				model.setContent(((TextInputControl) event.getSource()).getText())
			); //Update model whenever something is typed in the input field
		
		model.content.observable
			.filter(x -> x.isEmpty())
			.forEach(FXObservers.textField.text(logField)); //Update the TextField whenever the model content variable is cleared
		
		model.content.observable.forEach(input -> {
			if(input.isEmpty()){
				model.setError(String.format("Value may not be empty"));
			} else if(model.list.contains(input)){
				model.setError(String.format("List already contains %s", input));
			} else{
				model.setError("");
			}
		}); //update the error variable whenever the content changes
		
		Action1<TextInputControl> add = (input) -> {
			if(!model.list.contains(input.getText())) {
				model.list.add(input.getText());
				model.content.setValue("");
			}
		}; // onNext for whenever a value should be added
		
		Observable.merge(
				FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION),
				FXObservable.node(scene.lookup("#addLog"), ActionEvent.ACTION)
		)
		.map((o) -> (TextInputControl) scene.lookup("#logField"))
		.filter((input) -> !input.getText().isEmpty())
		.subscribe(add); //merge pressing enter on the TextField and pressing the add button, executing the add action
		
		FXObservable.node(scene.lookup("#sortAZ"), ActionEvent.ACTION)
		.map((event) -> (Comparator<String>) (x, y) -> x.compareTo(y))
		.mergeWith(FXObservable.node(scene.lookup("#sortZA"), ActionEvent.ACTION).map((event) -> (Comparator<String>) (x, y) -> y.compareTo(x)))
		.forEach((comparator) -> {
			Collections.sort(model.list, comparator);
		}); //merge the sort buttons, map them to Observable<Comparator<String>> then sort the list using this comparator
		
		stage.show();
	}
}
