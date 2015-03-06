package nl.tudelft.rxfx.observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("unchecked")
public class ObservableListVariable<A> extends AbstractObservableVariable<List<A>> implements ObservableList<A>{
	
	protected ObservableList<A> a;
	
	public ObservableListVariable(List<A> a) {
		super(a);
		this.a = a instanceof ObservableList ? (ObservableList<A>) a : FXCollections.observableList(a);
		this.a.addListener((ListChangeListener.Change<? extends A> change) -> subject.onNext(a));
	}
	
	public ObservableListVariable() {
		this(new ArrayList<A>());
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
	public <T> T[] toArray(T[] array) {
		return a.toArray(array);
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
	public boolean addAll(int index, Collection<? extends A> c) {
		return a.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return a.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return a.retainAll(c);
	}

	@Override
	public void clear() {
		a.clear();
	}

	@Override
	public A get(int index) {
		return a.get(index);
	}

	@Override
	public A set(int index, A element) {
		return a.set(index, element);
	}

	@Override
	public void add(int index, A element) {
		a.add(index, element);
	}

	@Override
	public A remove(int index) {
		return a.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return a.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return a.lastIndexOf(o);
	}

	@Override
	public ListIterator<A> listIterator() {
		return a.listIterator();
	}

	@Override
	public ListIterator<A> listIterator(int index) {
		return a.listIterator(index);
	}

	@Override
	public List<A> subList(int fromIndex, int toIndex) {
		return a.subList(fromIndex, toIndex);
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
	public void addListener(ListChangeListener<? super A> listener) {
		a.addListener(listener);
	}

	@Override
	public void removeListener(ListChangeListener<? super A> listener) {
		a.removeListener(listener);
	}

	
	@Override
	public boolean addAll(A... elements) {
		return a.addAll(elements);
	}

	@Override
	public boolean setAll(A... elements) {
		return a.setAll(elements);
	}

	@Override
	public boolean setAll(Collection<? extends A> col) {
		return a.setAll(col);
	}

	@Override
	public boolean removeAll(A... elements) {
		return a.removeAll(elements);
	}

	@Override
	public boolean retainAll(A... elements) {
		return a.retainAll(elements);
	}

	@Override
	public void remove(int from, int to) {
		a.remove(from, to);
	}
}