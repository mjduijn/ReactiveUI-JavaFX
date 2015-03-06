package nl.tudelft.rx.excel;

import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import nl.tudelft.rxfx.observable.ObservableMapVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;
import nl.tudelft.rxfx.observable.RXFXCollections;

public class Excel extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		final SpreadSheet spreadsheet = new SpreadSheet();
		TableView<ObservableMapVariable<String, Cell>> view = new TableView<>(spreadsheet.cells);
		
		
		Cell cell = new Cell(new ObservableVariable<String>(""), "");
		cell.setResult("DSAJKDAS");
		spreadsheet.cells.add(RXFXCollections.mapVariable(new HashMap<String, Cell>(){{put("Column1", cell);}}));
		
		TableColumn<ObservableMapVariable<String, Cell>, String> column = new TableColumn<>("Column1");
		
		view.getColumns().add(column);
		column.setCellValueFactory((s) -> new ReadOnlyStringWrapper(s.getValue().getOrDefault(s.getTableColumn().getText(), Cell.EMPTY).getResult()));
		
		Scene scene = new Scene(view, 800, 600);
		
		
		new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					spreadsheet.cells.add(RXFXCollections.mapVariable(new HashMap<String, Cell>(){{put("Column1", cell);}}));
					
				}
				
			};
		}.start();
		
		stage.setScene(scene);
		
		stage.show();
	}
}