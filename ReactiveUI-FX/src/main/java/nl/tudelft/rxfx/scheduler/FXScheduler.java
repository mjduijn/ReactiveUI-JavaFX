package nl.tudelft.rxfx.scheduler;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;

public class FXScheduler extends Scheduler{
	private static FXScheduler instance;
	
	private FXScheduler(){}
	
	public static FXScheduler getInstance(){
		if(instance == null){
			instance = new FXScheduler();
		}
		return instance;
	}

	@Override
	public Worker createWorker() {
		return new FXWorker();
	}
	
	public class FXWorker extends Worker{

		private Subscription subscription;
		
		public FXWorker() {
			this.subscription = new Subscription() {
				
				private boolean isUnsubscribed = false;
				
				@Override
				public void unsubscribe() {
					isUnsubscribed = true;
				}
				
				@Override
				public boolean isUnsubscribed() {
					return isUnsubscribed;
				}
			};
		}
		
		@Override
		public void unsubscribe() {
			subscription.unsubscribe();
		}

		@Override
		public boolean isUnsubscribed() {
			return subscription.isUnsubscribed();
		}

		@Override
		public Subscription schedule(Action0 action) {
			Platform.runLater(() -> {
				if(!isUnsubscribed()){
					action.call();
				}
			});
			return this;
		}

		@Override
		public Subscription schedule(Action0 action, long delayTime,
				TimeUnit unit) {
			Platform.runLater(() -> {
				try {
					Thread.sleep(unit.toMillis(delayTime));
					if(!isUnsubscribed()){
						action.call();
					}
				} catch (Exception e) {
				}
			});
			return this;
		}
	}
}
