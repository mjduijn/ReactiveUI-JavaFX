package nl.tudelft.rxfx.observable;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

public class FXObservable {
	
	public static <A> Observable<A> create(Func1<Subscriber<? super A>, Subscription> f){
		return Observable.create((Subscriber<? super A> subscriber) -> {
			Subscription sub = f.call(subscriber);
			subscriber.add(sub);
		});
	}
	
	public static <A extends Event> Observable<A> node(final Node node, EventType<A> type){
		return create((subscriber) -> {
			final EventHandler<A> handler = (a) -> subscriber.onNext(a);
			node.addEventHandler(type, handler);
			return new Action0Subscription(() -> node.removeEventHandler(type, handler));
		});
	}

	public static <A> Observable<A> javaObservable(javafx.beans.value.ObservableValue<A> ov){
		return Observable.create((subscriber) -> {
			ov.addListener(new javafx.beans.value.ChangeListener<A>() {
								@Override
				public void changed(ObservableValue<? extends A> observable,
						A oldValue, A newValue) {
					subscriber.onNext(newValue);
				}
			});
		});
	}
	
	public static <A extends Event> Observable<A> tableColumn(TableColumn<?, ?> column, EventType<A> type){
		return create((subscriber) -> {
			final EventHandler<A> handler = (a) -> subscriber.onNext(a);
			column.addEventHandler(type, handler);
			return new Action0Subscription(() -> column.removeEventHandler(type, handler));
		});
	}
}