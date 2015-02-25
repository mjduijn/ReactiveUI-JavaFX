package nl.tudelft.reactiveui.javafx.observable;

import javafx.beans.value.ObservableValue;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

public class ObservableMutableVariable <A extends ObservableValue<?>> extends Observable<A> 
	implements javafx.beans.value.ChangeListener<A> {
	private A a;
	private Subject<A, A> subject;
	
	private ObservableMutableVariable(final Subject<A, A> subject, A a) {
		super(sub -> subject.subscribe(next -> sub.onNext(next)));
		this.subject = subject;
		this.a = a;
		if(a instanceof javafx.beans.value.ObservableValue) {
			((javafx.beans.value.ObservableValue) a).addListener(this);
		}
		else 
//			throw new Exception("We should probably have a meaningful message here");
			assert(false); //TODO solve this w/o throws
	}
	
	public ObservableMutableVariable(A a) {
		this(BehaviorSubject.create(a), a);
	}

	public void setValue(A a){
		this.a = a;
		subject.onNext(a);
	}
	public void setVal(Object o){
//		((javafx.beans.value.ObservableValue) this.a).;
//		subject.onNext(a);
	}
	
	public A getValue(){
		return a;
	}

	@Override
	public void changed(ObservableValue<? extends A> observable, A oldValue,
			A newValue) {
		subject.onNext(newValue);		
	}

}
