package nl.tudelft.reactiveui.javafx.observable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

public class ObservableSetVariable<A> extends ObservableVariable<Set<A>> implements ObservableSet<A>{
	protected ObservableSet<A> a;
	
	public ObservableSetVariable(Set<A> a){
		super(a);
		this.a = a instanceof ObservableSet ? (ObservableSet<A>) a : FXCollections.observableSet(a);
		this.a.addListener((SetChangeListener.Change<? extends A> change) -> subject.onNext(a));
	}

	@Override
	public int size() {
		return a.size();
	}

	@Override
	public boolean isEmpty() {
		return a.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return a.contains(o);
	}

	@Override
	public Iterator<A> iterator() {
		return a.iterator();
	}

	@Override
	public Object[] toArray() {
		return a.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.a.toArray(a);
	}

	@Override
	public boolean add(A e) {
		return a.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return a.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return a.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends A> c) {
		return a.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return a.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return a.removeAll(c);
	}

	@Override
	public void clear() {
		a.clear();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		a.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		a.removeListener(listener);
	}

	@Override
	public void addListener(SetChangeListener<? super A> listener) {
		a.addListener(listener);
	}

	@Override
	public void removeListener(SetChangeListener<? super A> listener) {
		a.removeListener(listener);
	}
}
