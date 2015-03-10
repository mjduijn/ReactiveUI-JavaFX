package nl.tudelft.rx.excel;

import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableMapVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;

public class SpreadSheet {
	public final ObservableListVariable<ObservableMapVariable<String, Cell>> cells;
	public final ObservableVariable<Cell> selectedCell;
	
	public SpreadSheet() {
		this.cells = new ObservableListVariable<ObservableMapVariable<String,Cell>>();
		this.selectedCell = new ObservableVariable<Cell>();
	}
}
