package nl.tudelft.reactiveui.javafx.observable;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class ObservableVariable<A> extends AbstractObservableVariable<A> implements ObservableValue<A>{
	private Set<ChangeListener<? super A>> changeListeners;
	
	public ObservableVariable(A a) {
		super(a);
		this.changeListeners = new HashSet<>();
	}
	
	public void setValue(A a){
		subject.onNext(a);
		changeListeners.forEach((listener) -> listener.changed(this, this.a, a));
		this.a = a;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		//ObservableVariables cannot become invalid
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		//ObservableVariables cannot become invalid
	}

	@Override
	public void addListener(ChangeListener<? super A> listener) {
		changeListeners.add(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super A> listener) {
		changeListeners.remove(listener);
	}
}