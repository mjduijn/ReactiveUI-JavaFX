package nl.tudelft.reactiveui.javafx.observable;

import java.util.List;
import java.util.ListIterator;

import rx.Observer;


public class ObservableListIterator<A> extends ObservableIterator<A, List<A>> implements ListIterator<A>{

	protected ListIterator<A> delegate;
	
	public ObservableListIterator(ListIterator<A> delegate, Observer<List<A>> observer, List<A> value) {
		super(delegate, observer, value);
	}
	
	@Override
	public boolean hasPrevious() {
	}

	@Override
	public A previous() {
		return null;
	}

	@Override
	public int nextIndex() {
		return 0;
	}

	@Override
	public int previousIndex() {
		return 0;
	}

	@Override
	public void set(A e) {
	}

	@Override
	public void add(A e) {
	}

}
