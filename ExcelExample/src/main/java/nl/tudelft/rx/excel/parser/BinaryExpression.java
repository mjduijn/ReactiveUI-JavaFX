package nl.tudelft.rx.excel.parser;

public class BinaryExpression extends Expression{
	private Expression e1;
	private Operator op;
	private Expression e2;

	public BinaryExpression(Expression e1, Operator op, Expression e2) {
		this.e1 = e1;
		this.op = op;
		this.e2 = e2;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitBinaryExpression(this, obj);
	}

	public Expression getE1() {
		return e1;
	}

	public Operator getOp() {
		return op;
	}

	public Expression getE2() {
		return e2;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s, %s)", op, e1, e2);
	}
}