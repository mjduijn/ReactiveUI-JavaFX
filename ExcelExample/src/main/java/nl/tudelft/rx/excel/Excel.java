package nl.tudelft.rx.excel;

import java.util.Arrays;

import nl.tudelft.reactiveui.javafx.observable.FXObservable;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Excel extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		
		TableView<String> view = new TableView<String>(FXCollections.observableList(Arrays.asList("a", "b", "c")));


		TableColumn<String, String> column = new TableColumn<>("Column");
		
		view.getColumns().add(column);
		column.setCellValueFactory((s) -> new ReadOnlyStringWrapper(s.getValue()));
		
		column.setEditable(true);
		
		Scene scene = new Scene(view, 800, 600);
		
		stage.setScene(scene);
		
		stage.show();
	}
}