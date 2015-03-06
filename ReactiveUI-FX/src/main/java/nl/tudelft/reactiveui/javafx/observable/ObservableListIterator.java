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
		return delegate.hasPrevious();
	}

	@Override
	public A previous() {
		return delegate.previous();
	}

	@Override
	public int nextIndex() {
		return delegate.nextIndex();
	}

	@Override
	public int previousIndex() {
		return delegate.previousIndex();
	}

	@Override
	public void set(A e) {
		delegate.set(e);
	}

	@Override
	public void add(A e) {
		delegate.add(e);
	}
}
