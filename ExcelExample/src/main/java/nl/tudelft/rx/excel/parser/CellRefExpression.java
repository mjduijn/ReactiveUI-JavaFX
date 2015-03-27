package nl.tudelft.rx.excel.parser;

public class CellRefExpression extends Expression{
	public final String column;
	public final int row;
	
	public CellRefExpression(String column, int row) {
		this.column = column;
		this.row = row;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitCellRef(this, obj);
	}

	@Override
	public String toString() {
		return String.format("Cell(%s%d)", column, row);
	}
}
