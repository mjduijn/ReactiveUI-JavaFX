package nl.tudelft.rxfx.observable;

import java.util.List;
import java.util.Map;

public class RXFXCollections {
	public static <A> ObservableListVariable<A> listVariable(List<A> list){
		return new ObservableListVariable<A>(list);
	}
	
	
	public static <K,V> ObservableMapVariable<K, V> mapVariable(Map<K, V> map){
		return new ObservableMapVariable<K, V>(map);
	}
}
