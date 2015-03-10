package nl.tudelft.rx.excel.parser;

public class ExcelInterpreter implements ExcelVisitor<Integer, Void>{
	@Override
	public Integer visitBinaryExpression(BinaryExpression e, Void obj) {
		switch(e.getOp()){
		case ADD: return e.getE1().visit(this, obj) + e.getE2().visit(this, obj);
		case SUB: return e.getE1().visit(this, obj) - e.getE2().visit(this, obj);
		case MUL: return e.getE1().visit(this, obj) * e.getE2().visit(this, obj);
		case DIV: return e.getE1().visit(this, obj) / e.getE2().visit(this, obj);
		default: return 0;
		}
	}
	
	@Override
	public Integer visitCellRef(CellRefExpression e, Void obj) {
		return 1;
	}
	
	@Override
	public Integer visitIntegerExpression(IntegerExpression e, Void obj) {
		return e.getInteger();
	}
}
