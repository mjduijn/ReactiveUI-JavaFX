package nl.tudelft.rx.excel.parser;

public abstract class Expression {
	public abstract <R, A> R visit(ExcelVisitor<R, A> visitor, A obj);
}