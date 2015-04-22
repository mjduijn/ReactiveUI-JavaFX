package org.demo;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.FXObservers;

public class DemoApplication extends Application{

	static DemoTab0ViewModel dt0 = new DemoTab0ViewModel();
	static DemoTab1ViewModel dt1 = new DemoTab1ViewModel();
	
	public static void main(String[] args) {

//		new Thread(){
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					
//				}
//				
//			};
//		}.start();
		
		
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {

		Parent parent = new FXMLLoader().load(new FileInputStream("src/main/fxml/demo_ui.fxml"));
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		
		/////////////////////////////Tab 0
		//Observable slider
		FXObservable.javaObservable(((Slider)scene.lookup("#sliderVer")).valueProperty())
		.forEach(x -> dt0.slider.setValue(x.doubleValue()));
//		.subscribe(FXObserver.slider.setValue((Slider)scene.lookup("#sliderHor"))); //Direct subscription
		dt0.slider.observable.subscribe(FXObservers.slider.value((Slider)scene.lookup("#sliderHor")));
		
		//Progress mirrors vertical slider
		dt0.slider.observable
		.map(x -> x * 0.01)
		.subscribe(FXObservers.progressIndicator.progress((ProgressIndicator)scene.lookup("#progress")));
		
		//Progress bar
		dt0.slider.observable
		.map(x -> x * 0.01)
		.subscribe(FXObservers.progressBar.progress((ProgressBar)scene.lookup("#progressBar")));
		
		//Checkboxes
		FXObservable.node((CheckBox)scene.lookup("#cbUp"), MouseEvent.MOUSE_RELEASED)
		.subscribe(x -> dt0.checkBox.setValue(!((CheckBox)x.getSource()).selectedProperty().get()));

		dt0.checkBox.observable.subscribe(FXObservers.checkBox.selected((CheckBox)scene.lookup("#cbDown")));
		
		//Textfield
		FXObservable.node(scene.lookup("#textField"), KeyEvent.KEY_RELEASED)
		.filter(e -> e.getCode() == KeyCode.ENTER)
		.map(event -> ((TextInputControl) event.getSource()).getText())
		.subscribe(x -> dt0.choiceBox.add(x));
		
		//Choice box
		((ChoiceBox<String>)scene.lookup("#choiceBox")).setItems(dt0.choiceBox);
		
		///////////////// Tab 1
		FXObservable.node(scene.lookup("#tf1High"), KeyEvent.KEY_TYPED)
		.map(event -> ((TextInputControl) event.getSource()).getText() + event.getCharacter())
		.subscribe(x -> dt1.text.setValue(x));
		
//		FXObservable.node(scene.lookup("#tf1High"), KeyEvent.KEY_TYPED)
		
		dt1.text.observable.subscribe(FXObservers.textField.text((TextField)scene.lookup("#tf1Low")));

		//Datepicker observe
		FXObservable.node(scene.lookup("#datePicker"), ActionEvent.ACTION)
		.map(event -> ((DatePicker) event.getSource()).getValue())
		.filter(x -> x != null)
		.subscribe(x -> dt1.text.setValue(x.toString()));

		stage.show();
	}
}