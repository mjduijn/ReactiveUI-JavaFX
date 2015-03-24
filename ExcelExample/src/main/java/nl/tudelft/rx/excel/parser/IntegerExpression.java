package nl.tudelft.rx.excel.parser;

public class IntegerExpression extends Expression{
	public final int integer;

	public IntegerExpression(int integer) {
		this.integer = integer;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitIntegerExpression(this, obj);
	}
	
	@Override
	public String toString() {
		return "" + integer;
	}
}
