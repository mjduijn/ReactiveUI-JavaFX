package nl.tudelft.reactiveui.javafx.observable;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;
import rx.Observable.OnSubscribe;

public abstract class AbstractObservableVariable<A> extends Observable<A>{
	protected A a;
	protected Subject<A, A> subject;
	
	private AbstractObservableVariable(final Subject<A, A> subject, A a) {
		super(sub -> subject.subscribe(next -> sub.onNext(next)));
		this.subject = subject;
		this.a = a;
	}
	
	public AbstractObservableVariable(A a) {
		this(BehaviorSubject.create(a), a);
	}
	
	public A getValue(){
		return a;
	}
}
