package nl.tudelft.rx.excel;

import java.lang.reflect.Field;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.FXObserver;
import nl.tudelft.rxfx.observable.ObservableMapVariable;
import nl.tudelft.rxfx.observable.RXFXCollections;

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
			TableColumn<ObservableMapVariable<String, Cell>, String> column = new TableColumn<>("" + c);
			column.prefWidthProperty().set(100);
			column.setCellValueFactory((s) -> s.getValue().getOrDefault(s.getTableColumn().getText(), Cell.EMPTY).result);
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
			
			FXObservable.tableColumn(column, TableColumn.editCommitEvent()).subscribe(x -> System.out.println(x));
		}
		for(int i = nFrom ; i <= nTo ; i++){
			ObservableMapVariable<String, Cell> row = RXFXCollections.mapVariable(new HashMap<>());
			for(char c = cFrom ; c < cTo ; c++){
				Cell cell = new Cell();
				cell.setResult("bla");
				row.put(""+c, cell);
			}
			spreadsheet.cells.add(row);
		}
		stage.show();
	}
}