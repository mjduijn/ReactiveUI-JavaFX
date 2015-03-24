package nl.tudelft.rx.excel.parser;

import static org.javafp.parsecj.Combinators.*;
import static org.javafp.parsecj.Parser.*;
import static org.javafp.parsecj.Text.*;

import java.util.Optional;

import org.javafp.parsecj.ConsumedT;
import org.javafp.parsecj.Parser;
import org.javafp.parsecj.Parser.Ref;
import org.javafp.parsecj.Reply;
import org.javafp.parsecj.State;

@SuppressWarnings("unchecked")
public class ExcelParser {
	
	
	public static final Parser<Character, Expression> number = intr.bind((i) -> retn(new IntegerExpression(i)));
	
	public static final Parser<Character, Expression> cellRef = regex("[a-zA-Z]+").bind(column -> intr.bind(row -> retn(new CellRefExpression(column.toUpperCase(), row))));
	
	private static final Ref<Character, Expression> ref_exp = ref();
	
	
	public static final Parser<Character, Operator> opAdd = chr('+').bind(c -> retn(Operator.ADD));
	public static final Parser<Character, Operator> opSub = chr('-').bind(c -> retn(Operator.SUB));
	public static final Parser<Character, Operator> opMul = chr('*').bind(c -> retn(Operator.MUL));
	public static final Parser<Character, Operator> opDiv = chr('/').bind(c -> retn(Operator.DIV));
	
	public static final Parser<Character, Expression> operand = choice(
			number,
			cellRef
	).between(wspaces, wspaces);
	
	
	
	private static final Ref<Character, Expression> ref_addSubExp = ref();
	public static final Parser<Character, Expression> addSubExp = operand.bind(
		e1 -> option(
			opMul.or(opDiv).bind(
				op -> ref_addSubExp.bind(
					e2 -> retn(new BinaryExpression(e1, op, e2))
				)
			),
			e1
		)
	);
	
	private static final Ref<Character, Expression> ref_multDivExp = ref();
	public static final Parser<Character, Expression> multDivExp = addSubExp.bind(
		e1 -> option(
			opAdd.or(opSub).bind(
				op -> ref_multDivExp.bind(
					e2 -> retn(new BinaryExpression(e1, op, e2))
				)
			),
			e1
		)
	);
	
	
	public static final Parser<Character, Expression> exp = choice(
		multDivExp
	);
	
	//public static final Parser<Character, Optional<Expression>> parser = optionalOpt();
	public static final Parser<Character, Expression> parser = exp.between(retn(null), eof());
	
	
	public Reply<Character, Expression> parse(String input){
		return parser.parse(State.of(input));
	}
	
	static{
		ref_exp.set(exp);
		ref_addSubExp.set(addSubExp);
		ref_multDivExp.set(multDivExp);
	}
	
	public static void main(String[] args) throws Exception {
		String input = "6 - 2 * A85";
		Reply<Character, Expression> parsed = parser.parse(State.of(input));
		if(parsed.isOk()){
			Integer result = parsed.getResult().visit(new ExcelInterpreter(), null);
			System.out.printf("%s = %d", input, result);
		} else{
			System.out.println(parsed.getMsg());
		}
	}
	
	/*
	 * 2 + 3 * 4
	 * 
	 * Add(2, Mult(3,4))
	 * 
	 */
	
}
