package nl.tudelft.rx.excel.parser;

public class BinaryExpression extends Expression{
	public final Expression e1;
	public final Operator op;
	public final Expression e2;

	public BinaryExpression(Expression e1, Operator op, Expression e2) {
		this.e1 = e1;
		this.op = op;
		this.e2 = e2;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitBinaryExpression(this, obj);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s, %s)", op, e1, e2);
	}
}