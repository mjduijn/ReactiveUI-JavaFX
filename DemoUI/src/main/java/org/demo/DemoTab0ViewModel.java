package org.demo;

import java.util.LinkedList;

import nl.tudelft.rxfx.observable.ObservableListVariable;
import nl.tudelft.rxfx.observable.ObservableVariable;

public class DemoTab0ViewModel {
	public final ObservableVariable<Integer> progress;
	public final ObservableVariable<Double> slider;
	public final ObservableVariable<Boolean> checkBox;
	public final ObservableListVariable<String> choiceBox;
	
	
	public DemoTab0ViewModel() {
		progress = new ObservableVariable<Integer>(0);
		slider = new ObservableVariable<Double>(.0);
		checkBox = new ObservableVariable<Boolean>(false);
		choiceBox = new ObservableListVariable<String>(new LinkedList<String>());
	}
}
