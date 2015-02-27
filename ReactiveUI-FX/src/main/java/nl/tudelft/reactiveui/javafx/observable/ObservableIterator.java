package nl.tudelft.reactiveui.javafx.observable;

import java.util.Iterator;

import rx.Observer;

public class ObservableIterator<A, B> implements Iterator<A>{
	private Iterator<A> delegate;
	private Observer<B> observer;
	private B value;
	
	public ObservableIterator(Iterator<A> delegate, Observer<B> observer, B value) {
		this.delegate = delegate;
		this.observer = observer;
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return delegate.hasNext();
	}
	
	@Override
	public A next() {
		return delegate.next();
	}
	
	@Override
	public void remove(){
		delegate.remove();
		observer.onNext(value);
	}
}
