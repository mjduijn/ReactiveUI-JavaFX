package nl.tudelft.rx.excel.parser;

public class ExcelInterpreter implements ExcelVisitor<Integer, Void>{
	@Override
	public Integer visitBinaryExpression(BinaryExpression e, Void obj) {
		switch(e.op){
		case ADD: return e.e1.visit(this, obj) + e.e2.visit(this, obj);
		case SUB: return e.e1.visit(this, obj) - e.e2.visit(this, obj);
		case MUL: return e.e1.visit(this, obj) * e.e2.visit(this, obj);
		case DIV: return e.e1.visit(this, obj) / e.e2.visit(this, obj);
		default: return 0;
		}
	}
	
	@Override
	public Integer visitCellRef(CellRefExpression e, Void obj) {
		throw new RuntimeException("Unevaluated cellref");
	}
	
	@Override
	public Integer visitIntegerExpression(IntegerExpression e, Void obj) {
		return e.integer;
	}
}
