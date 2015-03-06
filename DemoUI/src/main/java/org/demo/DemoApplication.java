package org.demo;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.tudelft.rxfx.observable.FXObservable;
import nl.tudelft.rxfx.observable.FXObserver;

public class DemoApplication extends Application{

	static DemoTab0ViewModel dt0 = new DemoTab0ViewModel();
	
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

	@Override
	public void start(Stage stage) throws Exception {

		Parent parent = new FXMLLoader().load(new FileInputStream("src/main/fxml/demo_ui.fxml"));
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		
		//Observable sldier
		FXObservable.node((Slider)scene.lookup("#sliderVer"), MouseEvent.MOUSE_DRAGGED)
		.map(e -> ((Slider)e.getSource()).getValue())
//		.subscribe(FXObserver.slider.setValue((Slider)scene.lookup("#sliderHor"))); //Direct subscription
		.subscribe(x -> dt0.slider.setValue(x));
		dt0.slider.observable.subscribe(FXObserver.slider.setValue((Slider)scene.lookup("#sliderHor")));
		
		//Progress mirrors vertical slider
		dt0.slider.observable
		.map(x -> x * 0.01)
		.subscribe(FXObserver.progressIndicator.setProgress((ProgressIndicator)scene.lookup("#progress")));
		
		//Progress bar
		dt0.slider.observable
		.map(x -> x * 0.01)
		.subscribe(FXObserver.progressBar.setProgress((ProgressBar)scene.lookup("#progressBar")));
		
		//Textfield
		FXObservable.node(scene.lookup("#textField"), KeyEvent.KEY_RELEASED)
		.filter(e -> e.getCode() == KeyCode.ENTER)
		.map(event -> ((TextInputControl) event.getSource()).getText())
		.doOnEach(x -> System.out.println(x))
		.subscribe(x -> dt0.choiceBox.add(x));
		
		//Choice box
		ChoiceBox<String> cb = (ChoiceBox)scene.lookup("#choiceBox");
		cb.setItems(dt0.choiceBox);
		
		stage.show();
	}
}