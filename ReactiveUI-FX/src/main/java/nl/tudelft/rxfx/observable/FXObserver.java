package nl.tudelft.rxfx.observable;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.util.Duration;
import rx.functions.Action0;
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
	
	public static class webEngine {
		public static Action1<String> load(WebEngine we){
			return (newValue) -> we.load(newValue);
		}
	}
	
	public static class tabPane {
		public static Action1<Integer> setSelected(TabPane tab){
			return (newValue) -> tab.getSelectionModel().select(newValue);
		}
	}
	
	public static class textInputControl {
		public static Action1<String> setText(TextInputControl tic) {
			return (newValue) -> tic.setText(newValue);			
		}
	}	
	public static class textField extends textInputControl {}
	public static class passwordField extends textInputControl {}
	
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
	
	public static class datePicker {
		public static Action1<LocalDate> setDate(DatePicker d) {
			return (newValue) -> d.setValue(newValue);
		}		
	}
	
	public static class toggleButton {
		public static Action1<Boolean> setSelected(ToggleButton tb) {
			return (newValue) -> tb.setSelected(newValue);
		}
	}

	public static class scrollBar {
		public static Action1<Double> setValue(ScrollBar sb) {
			return (newValue) -> sb.setValue(newValue);
		}		
	}
	
	public static class radioButton {
		public static Action1<Boolean> setSelected(RadioButton rb) {
			return (newValue) -> rb.setSelected(newValue);
		}		
	}
	
	public static class pagination {
		public static Action1<Integer> setSelected(Pagination p) {
			return (newValue) -> p.setCurrentPageIndex(newValue);
		}		
	}
	
	public static class mediaPlayer {
		public static Action0 pause (MediaPlayer mp) {
			return () -> mp.pause();
		}
		public static Action0 play (MediaPlayer mp) {
			return () -> mp.play();
		}
		public static Action0 stop (MediaPlayer mp) {
			return () -> mp.stop();
		}
		public static Action1 <Duration> seek (MediaPlayer mp) {
			return (newValue) -> mp.seek(newValue);
		}
	}
	
	public static class imageView {
		public static Action1 <Image> setImage(ImageView iv) {
			return (newValue) -> iv.setImage(newValue);
		}
	}
	
	public static class hyperlink {
		public static Action1<Boolean> setVisited(Hyperlink h) {
			return (value) -> h.setVisited(value);
		}
	}
	
	public static class HTMLeditor {
		public static Action1<String> setVisited(HTMLEditor h) {
			return (value) -> h.setHtmlText(value);
		}
	}

	public static class selectionModel { //Used in combo box and choice box
		public static Action1<Integer> setVisited(SelectionModel<?> sm) {
			return (value) -> sm.select(value);
		}
	}
	
	public static class colorPicker {
		public static Action1<Color> setVisited(ColorPicker cp) {
			return (value) -> cp.setValue(value);
		}
	}
	
	
}
