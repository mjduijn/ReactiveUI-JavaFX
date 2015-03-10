package nl.tudelft.rx.excel.parser;

public enum Operator {
	ADD("+"),
	SUB("-"),
	MUL("*"),
	DIV("/")
	;
	
	private String operator;
	private Operator(String operator){
		this.operator = operator;
	}	
}