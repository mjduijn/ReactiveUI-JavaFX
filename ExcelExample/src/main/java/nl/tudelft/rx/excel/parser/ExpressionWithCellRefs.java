package nl.tudelft.rx.excel.parser;

import java.util.List;

public class ExpressionWithCellRefs {
	public final Expression exp;
	public final List<CellRefExpression> cellRefs;

	public ExpressionWithCellRefs(Expression exp, List<CellRefExpression> cellRefs) {
		this.exp = exp;
		this.cellRefs = cellRefs;
	}
}
