package nl.tudelft.reactiveui.javafx.observable;

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
	public ObservableListVariable(List<A> a) {
		super(a);
		this.a = FXCollections.observableList(a);
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
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(A e) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends A> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends A> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
	}

	@Override
	public A get(int index) {
		return null;
	}

	@Override
	public A set(int index, A element) {
		return null;
	}

	@Override
	public void add(int index, A element) {
	}

	@Override
	public A remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<A> listIterator() {
		return null;
	}

	@Override
	public ListIterator<A> listIterator(int index) {
		return null;
	}

	@Override
	public List<A> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public void addListener(InvalidationListener listener) {
	}

	@Override
	public void removeListener(InvalidationListener listener) {
	}

	@Override
	public void addListener(ListChangeListener<? super A> listener) {
	}

	@Override
	public void removeListener(ListChangeListener<? super A> listener) {
	}

	
	@Override
	public boolean addAll(A... elements) {
		return false;
	}

	@Override
	public boolean setAll(A... elements) {
		return false;
	}

	@Override
	public boolean setAll(Collection<? extends A> col) {
		return false;
	}

	@Override
	public boolean removeAll(A... elements) {
		return false;
	}

	@Override
	public boolean retainAll(A... elements) {
		return false;
	}

	@Override
	public void remove(int from, int to) {
	}
	
	
}