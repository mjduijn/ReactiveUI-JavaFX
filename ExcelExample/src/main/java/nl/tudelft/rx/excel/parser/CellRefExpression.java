package nl.tudelft.rx.excel.parser;

public class CellRefExpression extends Expression{
	private String column;
	private int row;
	
	public CellRefExpression(String column, int row) {
		this.column = column;
		this.row = row;
	}
	
	@Override
	public <R, A> R visit(ExcelVisitor<R, A> visitor, A obj) {
		return visitor.visitCellRef(this, obj);
	}

	public int getRow() {
		return row;
	}

	public String getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return String.format("Cell(%s%d)", column, row);
	}
}
