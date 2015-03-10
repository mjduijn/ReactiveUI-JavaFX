package org.demo;

import nl.tudelft.rxfx.observable.ObservableVariable;

public class DemoTab1ViewModel {
	public final ObservableVariable<String> text;
	public final ObservableVariable<String> date;
	
	public DemoTab1ViewModel() {
		text = new ObservableVariable<String>("");
		date = new ObservableVariable<String>("");
	}
}
