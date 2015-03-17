package nl.tudelft.rxfx.observable;

import rx.Observable;
import rx.Subscriber;
import javafx.beans.value.ObservableValue;
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

	public static <A> Observable<A> javaObservable(javafx.beans.value.ObservableValue<A> ov){
		return Observable.create((subscriber) -> {
			ov.addListener(new javafx.beans.value.ChangeListener<A>() {
								@Override
				public void changed(ObservableValue<? extends A> observable,
						A oldValue, A newValue) {
					System.out.println(newValue);
					subscriber.onNext(newValue);
				}
			});
		});
	}
}