package log;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogApplication extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = new FXMLLoader().load(new FileInputStream("src/main/fxml/log-controller.fxml"));
		stage.setScene(new Scene(parent));
		stage.show();
	}
}