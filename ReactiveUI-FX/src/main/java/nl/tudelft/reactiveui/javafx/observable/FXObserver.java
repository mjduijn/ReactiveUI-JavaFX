package nl.tudelft.reactiveui.javafx.observable;

import javafx.scene.Node;
import javafx.scene.control.Label;
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
}
