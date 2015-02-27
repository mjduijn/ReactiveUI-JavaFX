package nl.tudelft.reactiveui.javafx.observable;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;
import rx.Observable.OnSubscribe;

public abstract class AbstractObservableVariable<A>{
	protected A a;
	protected final Subject<A, A> subject;
	
	public final Observable<A> observable;
	
	private AbstractObservableVariable(final Subject<A, A> subject, A a) {
		this.subject = subject;
		this.a = a;
		this.observable = subject;
	}
	
	public AbstractObservableVariable(A a) {
		this(BehaviorSubject.create(a), a);
	}
	
	public A getValue(){
		return a;
	}
}
