package nl.tudelft.reactiveui.javafx.observable;

import rx.Observable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;

public class FXObservable {
	public static <A extends Event> Observable<A> node(Node node, EventType<A> type){
		return Observable.create((subscriber) -> {
			EventHandler<A> handler = (a) -> subscriber.onNext(a);
			node.addEventHandler(type, handler);
		});
	}
}