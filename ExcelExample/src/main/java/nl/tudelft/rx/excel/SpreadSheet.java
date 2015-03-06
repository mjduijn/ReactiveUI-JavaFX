package nl.tudelft.rx.excel;

import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableMapVariable;

public class SpreadSheet {
	public final ObservableListVariable<ObservableMapVariable<String, Cell>> cells;
	
	public SpreadSheet() {
		this.cells = new ObservableListVariable<ObservableMapVariable<String,Cell>>();
	}
}
