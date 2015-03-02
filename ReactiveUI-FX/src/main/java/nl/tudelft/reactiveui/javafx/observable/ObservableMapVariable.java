package nl.tudelft.reactiveui.javafx.observable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

@SuppressWarnings("unchecked")
public class ObservableMapVariable<K, V> extends AbstractObservableVariable<Map<K, V>> implements ObservableMap<K, V> {
	protected ObservableMap<K, V> a;
	
	public ObservableMapVariable(Map<K, V> a) {
		super(a);
		this.a = FXCollections.observableMap(a);
	}

	@Override
	public int size() {
		return this.a.size();
	}

	@Override
	public boolean isEmpty() {
		return this.a.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.a.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.a.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return this.a.get(key);
	}

	@Override
	public V put(K key, V value) {
		return this.a.put(key, value);
	}

	@Override
	public V remove(Object key) {
		return this.a.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		this.a.putAll(m);
	}

	@Override
	public void clear() {
		this.a.clear();
	}

	@Override
	public Set<K> keySet() {
		return this.a.keySet();
	}

	@Override
	public Collection<V> values() {
		return this.a.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return this.a.entrySet();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		this.a.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		this.a.removeListener(listener);		
	}

	@Override
	public void addListener(MapChangeListener<? super K, ? super V> listener) {
		this.a.addListener(listener);
	}

	@Override
	public void removeListener(MapChangeListener<? super K, ? super V> listener) {
		this.a.removeListener(listener);		
	}

}
