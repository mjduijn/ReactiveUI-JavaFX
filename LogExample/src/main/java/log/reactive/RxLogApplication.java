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
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import log.LogViewModel;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.FXObserver;
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
		
		values.addAll(Arrays.asList(new String[]{"bla", "bli", "blo"}));
		
		Iterator<String> iterator = values.iterator();
		iterator.next();
		iterator.remove();
		
		
		
		
		LogViewModel model = new LogViewModel(values);
		
		model.getList().observable.subscribe(list -> System.out.println(list));
		
		model.getList().add("lalalalal");
		
		model.getError().observable.subscribe(FXObserver.label.text(errorLabel));
		model.getError().observable.map(s -> s.isEmpty()).subscribe(FXObserver.node.enabled(scene.lookup("#addLog")));
		
		//FXObservable.javaObservable(model.getError2().observable)
		//.subscribe(System.out::println);
		
		FXObservable.node(scene.lookup("#logField"), KeyEvent.KEY_RELEASED)
		.map(event -> ((TextInputControl) event.getSource()).getText())
		.forEach(input -> {
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
		
		Observable.merge(
				FXObservable.node(scene.lookup("#logField"), ActionEvent.ACTION),
				FXObservable.node(scene.lookup("#addLog"), ActionEvent.ACTION)
		)
		.map((o) -> (TextInputControl) scene.lookup("#logField"))
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
