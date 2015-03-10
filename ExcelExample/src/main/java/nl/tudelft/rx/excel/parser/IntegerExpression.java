package nl.tudelft.rx.excel.parser;

public class IntegerExpression extends Expression{
	private int integer;

	public IntegerExpression(int integer) {
		this.integer = integer;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitIntegerExpression(this, obj);
	}
	
	public int getInteger() {
		return integer;
	}
	
	@Override
	public String toString() {
		return "" + integer;
	}
}
