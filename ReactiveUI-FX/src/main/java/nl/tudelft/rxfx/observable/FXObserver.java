package nl.tudelft.rxfx.observable;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import rx.functions.Action1;

public class FXObserver {
	
	public static class node {
		public static Action1<Boolean> enabled(Node node){
			return (newValue) -> node.setDisable(!newValue);
		}
		
		public static Action1<Boolean> disabled(Node node){
			return (newValue) -> node.setDisable(newValue);
		}

		public static Action1<Boolean> setVisible(Node node){
			return (newValue) -> node.setVisible(!newValue);
		}
		
	}
	
	public static class label{
		public static Action1<String> text(Label label){
			return (newValue) -> label.setText(newValue);
		}
	}
	
	public static class slider {
		public static Action1<Double> setValue(Slider slider){
			return (newValue) -> slider.setValue(newValue);
		}
	}
	
	public static class progressIndicator {
		public static Action1<Double> setProgress(ProgressIndicator progress) {
			return (newValue) -> progress.setProgress(newValue);
		}
	}
	
	public static class checkBox {
		public static Action1<Boolean> setSelected(CheckBox cb) {
			return (newValue) -> cb.setSelected(newValue);
		}
	}
	
	public static class progressBar {
		public static Action1<Double> setProgress(ProgressBar progress) {
			return (newValue) -> progress.setProgress(newValue);
		}
	}
}
