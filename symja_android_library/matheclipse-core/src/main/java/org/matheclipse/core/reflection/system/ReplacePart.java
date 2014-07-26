package org.matheclipse.core.reflection.system;

import org.matheclipse.core.eval.exception.Validate;
import org.matheclipse.core.eval.interfaces.IFunctionEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;

public class ReplacePart implements IFunctionEvaluator {

	public ReplacePart() {
	}

	public IExpr evaluate(final IAST ast) {
		Validate.checkRange(ast, 3, 4);
		
		if (ast.size() == 4) {
			if (ast.arg3().isList()) {
				IExpr result = ast.arg1();
				for (IExpr subList : (IAST) ast.arg3()) {
					IExpr expr = result.replacePart(F.Rule(subList, ast.get(2)));
					if (expr != null) {
						result = expr;
					}
				}
				return result;
			}
			final IExpr result = ast.arg1().replacePart(F.Rule(ast.arg3(), ast.arg2()));
			return (result == null) ? ast.arg1() : result;
		}
		if (ast.arg2().isList()) {
			IExpr result = ast.arg1();
			for (IExpr subList : (IAST) ast.arg2()) {
				if (subList.isRuleAST()) {
					IExpr expr = result.replacePart((IAST) subList);
					if (expr != null) {
						result = expr;
					}
				}
			}
			return result;
		}
		IExpr result = ast.arg1();
		if (ast.arg2().isRuleAST()) {
			result = ast.arg1().replacePart((IAST) ast.arg2());
			return (result == null) ? ast.arg1() : result;
		}
		return result;

	}

	public IExpr numericEval(final IAST functionList) {
		return evaluate(functionList);
	}

	public void setUp(final ISymbol symbol) {
	}
}
