package nl.tudelft.reactiveui.javafx.observable;


public class ObservableVariable<A> extends AbstractObservableVariable<A>{
	public ObservableVariable(A a) {
		super(a);
	}
	
	public void setValue(A a){
		this.a = a;
		subject.onNext(a);
	}
}