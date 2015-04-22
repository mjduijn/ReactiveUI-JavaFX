package log;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableMapVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;

public class CovarianceProblem extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ObservableVariable<Boolean> obs = new ObservableVariable<>(true);
		obs.observable.forEach(b -> {
			obs.setValue(!b);
		});
		
		ObservableList<ObservableMapVariable<String, Integer>> list = new ObservableListVariable<>();
		ObservableList<Map<String, Integer>> list2 = (ObservableList<Map<String, Integer>>) ((Object) list);
		TableView<Map<String, Integer>> tableView = new TableView<Map<String, Integer>>(list2);
		tableView.getItems().add(new HashMap<String, Integer>());
		for(ObservableMapVariable<String,Integer> m : list){
			System.out.println(m);
		}
	}
}
