package nl.tudelft.rx.excel;

import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
		view.setEditable(true);
		
		//Rectangle2D rect = Screen.getScreens().get(0).getVisualBounds();
		//Scene scene = new Scene(view, rect.getWidth(), rect.getHeight());
		//stage.setMaximized(true);
		Scene scene = new Scene(view, 800, 600);
		stage.setScene(scene);
		
		char cFrom = 'A';
		char cTo = 'Z';
		char nFrom = 0;
		char nTo = 10;
		
		for(char c = cFrom; c <= cTo; c++){
			TableColumn<ObservableMapVariable<String, Cell>, Cell> column = new TableColumn<>("" + c);
			column.prefWidthProperty().set(100);
			column.setCellValueFactory((s) -> new ReadOnlyObjectWrapper<>(s.getValue().getOrDefault(s.getTableColumn().getText(), Cell.empty())));
			column.setCellFactory((x) -> {
				TextFieldTableCell<ObservableMapVariable<String, Cell>, Cell> cell = new TextFieldTableCell<ObservableMapVariable<String, Cell>, Cell>(new StringConverter<Cell>() {
					@Override
					public String toString(Cell object) {
						return object.getResult();
					}
	
					@Override
					public Cell fromString(String string) {
						return Cell.empty();
					}
				});
				return cell;
			});
			view.getColumns().add(column);
			FXObservable.tableColumn(column, TableColumn.editCommitEvent()).subscribe(event -> {
				spreadsheet.cells.get(event.getTablePosition().getRow()).get(event.getTableColumn().getText()).setFormula(event.getNewValue().toString());
			});
		}
		Func2<Integer, String, Cell> cellSearcher = (row, column) -> spreadsheet.cells.get(row).get(column);
		for(int i = nFrom ; i <= nTo ; i++){
			ObservableMapVariable<String, Cell> row = RXFXCollections.mapVariable(new HashMap<>());
			for(char c = cFrom ; c < cTo ; c++){
				Cell cell = new Cell(i, ""+c, cellSearcher);
				cell.setResult("0");
				cell.result.observable.subscribe(x -> System.out.println(x));
				row.put(""+c, cell);
			}
			spreadsheet.cells.add(row);
		}
		stage.show();
	}
}