package nl.tudelft.rx.excel.parser;

public interface ExcelVisitor<R, A> {
	public R visitIntegerExpression(IntegerExpression e, A obj);
	public R visitBinaryExpression(BinaryExpression e, A obj);
	public R visitCellRef(CellRefExpression e, A obj);
}