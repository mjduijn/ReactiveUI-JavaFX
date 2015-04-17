package nl.tudelft.rxfx.observable;

import javafx.event.EventHandler;
import javafx.scene.Node;
import rx.Subscription;
import rx.functions.Action0;

public class Action0Subscription implements Subscription{
	private Action0 onUnsubscribed;
	private boolean isSubscribed;
	
	public Action0Subscription(Action0 onUnsubscribed) {
		this.onUnsubscribed = onUnsubscribed;
		this.isSubscribed = true;
	}

	@Override
	public boolean isUnsubscribed() {
		return isSubscribed;
	}
	
	@Override
	public void unsubscribe() {
		System.out.println("unsubscribing");
		this.isSubscribed = false;
		onUnsubscribed.call();
	}
}
