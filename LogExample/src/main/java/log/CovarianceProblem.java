package log;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableMapVariable;

public class CovarianceProblem {
	public static void main(String[] args){
		ObservableList<ObservableMapVariable<String, Integer>> list = new ObservableListVariable<>();
		
		/* Type error
		TableView<Map<String, Integer>> tableView = new TableView<Map<String, Integer>>(list);
		*/
	}
}
