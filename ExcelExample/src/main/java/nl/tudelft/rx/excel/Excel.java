package nl.tudelft.rx.excel;

import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.ObservableMapVariable;
import nl.tudelft.rxfx.observable.RXFXCollections;
import rx.functions.Func2;

public class Excel extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@SuppressWarnings("serial")
	@Override
	public void start(Stage stage) throws Exception {
		final SpreadSheet spreadsheet = new SpreadSheet();
		TableView<ObservableMapVariable<String, Cell>> view = new TableView<>(spreadsheet.cells);
		
		view.setSortPolicy(x -> false); //Disable sorting on columns for now
		view.setEditable(true);
		Scene scene = new Scene(view, 800, 600);
		stage.setScene(scene);
		
		char cFrom = 'A';
		char cTo = 'Z';
		char nFrom = 0;
		char nTo = 10;
		
		for(char c = cFrom; c <= cTo; c++){
			TableColumn<ObservableMapVariable<String, Cell>, String> column = new TableColumn<>("" + c);
			column.prefWidthProperty().set(100);
			column.setCellValueFactory((s) -> new ReadOnlyStringWrapper(s.getValue().getOrDefault(s.getTableColumn().getText(), Cell.empty()).getResult()));//new ReadOnlyObjectWrapper<>(s.getValue().getOrDefault(s.getTableColumn().getText(), Cell.empty())));
			column.setCellFactory((x) -> {
				TextFieldTableCell<ObservableMapVariable<String, Cell>, String> cell = new TextFieldTableCell<ObservableMapVariable<String, Cell>, String>(new StringConverter<String>() {
					@Override
					public String toString(String object) {
						return object;
					}
	
					@Override
					public String fromString(String string) {
						return string;
					}
				});
				return cell;
			});
			view.getColumns().add(column);
			FXObservable.tableColumn(column, TableColumn.editCommitEvent()).subscribe(event -> {
				ObservableMapVariable<String, Cell> row = spreadsheet.cells.get(event.getTablePosition().getRow());
				Cell updatedCell = row.get(event.getTableColumn().getText()); 
				updatedCell.setFormula(event.getNewValue().toString());
				spreadsheet.cells.set(event.getTablePosition().getRow(), row); //tricking the ObservableList to detect a change
			});
		}
		Func2<Integer, String, Cell> cellSearcher = (row, column) -> spreadsheet.cells.get(row).get(column);
		for(int i = nFrom ; i <= nTo ; i++){
			ObservableMapVariable<String, Cell> row = RXFXCollections.mapVariable(new HashMap<>());
			for(char c = cFrom ; c <= cTo ; c++){
				Cell cell = new Cell(i, ""+c, cellSearcher);
				cell.setFormula("0");
				cell.result.observable.subscribe(x -> System.out.println("result is now: " + x));
				row.put(""+c, cell);
			}
			spreadsheet.cells.add(row);
		}
		stage.show();
	}
}