package nl.tudelft.rxfx.observable;

import java.util.Iterator;

import rx.Observer;

public class ObservableIterator<A, B> implements Iterator<A>{
	protected Iterator<A> delegate;
	protected Observer<B> observer;
	protected B value;
	
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
