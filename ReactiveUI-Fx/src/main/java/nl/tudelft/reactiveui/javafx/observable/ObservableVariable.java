package nl.tudelft.reactiveui.javafx.observable;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

public class ObservableVariable<A> extends Observable<A>{
	private A a;
	private Subject<A, A> subject;
	
	private ObservableVariable(final Subject<A, A> subject, A a) {
		super(sub -> subject.subscribe(next -> sub.onNext(next)));
		this.subject = subject;
		this.a = a;
	}
	
	public ObservableVariable(A a) {
		this(BehaviorSubject.create(a), a);
	}
	
	public void setValue(A a){
		this.a = a;
		subject.onNext(a);
	}
	
	public A getValue(){
		return a;
	}
}