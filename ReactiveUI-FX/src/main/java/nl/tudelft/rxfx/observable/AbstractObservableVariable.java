package nl.tudelft.rxfx.observable;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;
import rx.Observable.OnSubscribe;

public abstract class AbstractObservableVariable<A>{
	protected A value;
	protected final Subject<A, A> subject;
	public final Observable<A> observable;
	
	private AbstractObservableVariable(final Subject<A, A> subject, A value) {
		this.subject = subject;
		this.value = value;
		this.observable = subject;
		subject.onNext(value);
	}
	
	public AbstractObservableVariable(A a) {
		this(BehaviorSubject.create(a), a);
	}
	
	public A getValue(){
		return value;
	}
}
