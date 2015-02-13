package org.matheclipse.core.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.visit.IVisitor;
import org.matheclipse.core.visit.IVisitorBoolean;
import org.matheclipse.core.visit.IVisitorInt;
import org.matheclipse.core.visit.IVisitorLong;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import edu.jas.structure.GcdRingElem;

/**
 * 
 * (I)nterface for a mathematical (Expr)ession
 * 
 */
public interface IExpr extends Comparable<IExpr>, GcdRingElem<IExpr>, Serializable {

	public final static int ASTID = 512;

	public final static int COMPLEXID = 32;

	public final static int DOUBLECOMPLEXID = 4;

	public final static int DOUBLEID = 2;

	public final static int FRACTIONID = 16;

	public final static int INTEGERID = 8;

	public final static int METHODSYMBOLID = 1024;

	public final static int PATTERNID = 256;

	public final static int STRINGID = 64;

	public final static int SYMBOLID = 128;

	/**
	 * Accept a visitor with return type T
	 */
	public <T> T accept(IVisitor<T> visitor);

	/**
	 * Accept a visitor with return type <code>boolean</code>
	 */
	public boolean accept(IVisitorBoolean visitor);

	/**
	 * Accept a visitor with return type <code>int</code>
	 * 
	 * @param visitor
	 * @return
	 */
	public int accept(IVisitorInt visitor);

	/**
	 * Accept a visitor with return type <code>long</code>
	 * 
	 * @param visitor
	 * @return
	 */
	public long accept(IVisitorLong visitor);

	public IExpr and(final IExpr that);

	/**
	 * @param leaves
	 * @return an IExpr instance with the current expression as head(), and leaves as leaves().
	 */
	public IExpr apply(IExpr... leaves);

	/**
	 * @param leaves
	 * @return an IExpr instance with the current expression as head(), and leaves as leaves().
	 */
	public IExpr apply(List<? extends IExpr> leaves);

	public Object asType(Class<?> clazz);

	/**
	 * Compares this expression with the specified expression for order. Returns a negative integer, zero, or a positive integer as
	 * this expression is canonical less than, equal to, or greater than the specified expression.
	 */
	@Override
	public int compareTo(IExpr obj);

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this / that)</code>. Calculates
	 * <code>F.eval(F.Times(this, F.Power(that, F.CN1)))</code> in the common case and uses a specialized implementation for derived
	 * number classes.
	 * 
	 * @param that
	 * @return
	 */
	@Override
	public IExpr divide(IExpr that);

	/**
	 * Evaluate an expression
	 * 
	 * @param engine
	 *            the evaluation engine
	 * @return the evaluated Object or <code>null</code> if the evaluation is not possible (i.e. the evaluation doesn't change the
	 *         object).
	 */
	public IExpr evaluate(EvalEngine engine);

	/**
	 * Return the <code>FullForm()</code> of this expression
	 */
	public String fullFormString();

	/**
	 * 
	 * Get the element at the specified <code>index</code> if this object is of type <code>IAST</code>.
	 * 
	 * @param index
	 * @return
	 */
	public IExpr getAt(final int index);

	/**
	 * If this object is an instance of <code>IAST</code> get the first element (offset 0) of the <code>IAST</code> list (i.e.
	 * get(0) ).
	 * 
	 * @return the head of the expression, which must not be null.
	 */
	public IExpr head();

	/**
	 * A unique integer ID for the implementation of this expression
	 * 
	 * @return a unique integer id for the implementation of this expression
	 */
	public int hierarchy();

	/**
	 * Return the internal Java form of this expression.
	 * 
	 * @param symbolsAsFactoryMethod
	 *            if <code>true</code> use the <code>F.symbol()</code> method, otherwise print the symbol name.
	 * @param depth
	 *            the recursion depth of this call. <code>0</code> indicates &quot;recurse without a limit&quot;.
	 */
	public String internalFormString(boolean symbolsAsFactoryMethod, int depth);

	/**
	 * Returns the multiplicative inverse of this object. It is the object such as <code>this.times(this.inverse()) == ONE </code>,
	 * with <code>ONE</code> being the multiplicative identity. Calculates <code>F.eval(F.Power(this, F.CN1))</code> in the common
	 * case and uses a specialized implmentation for derived number classes.
	 * 
	 * @return <code>ONE / this</code>.
	 */
	@Override
	IExpr inverse();

	/**
	 * Test if this expression is the function <code>And[&lt;arg&gt;,...]</code>
	 * 
	 */
	public boolean isAnd();

	/**
	 * Test if this expression is the function <code>ArcCos[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcCos();

	/**
	 * Test if this expression is the function <code>ArcCosh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcCosh();

	/**
	 * Test if this expression is the function <code>ArcSin[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcSin();

	/**
	 * Test if this expression is the function <code>ArcSinh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcSinh();

	/**
	 * Test if this expression is the function <code>ArcTan[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcTan();

	/**
	 * Test if this expression is the function <code>ArcTanh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isArcTanh();

	/**
	 * Test if this expression is an AST list, which contains a <b>header element</b> (i.e. the function name) at index position
	 * <code>0</code> and some optional <b>argument elements</b> at the index positions <code>1..n</code>. Therefore this expression
	 * is no <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 */
	public boolean isAST();

	/**
	 * Test if this expression is an AST list, which contains the given <b>header element</b> at index position <code>0</code> and
	 * some optional <b>argument elements</b> at the index positions <code>1..(size()-1)</code>. Therefore this expression is not an
	 * <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 * 
	 */
	public boolean isAST(IExpr header);

	/**
	 * Test if this expression is an AST list, which contains the given <b>header element</b> at index position <code>0</code> and
	 * optional <b>argument elements</b> at the index positions <code>1..(length-1)</code>. Therefore this expression is not an
	 * <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 */
	public boolean isAST(IExpr header, int length);

	/**
	 * Test if this expression is an AST list, which contains the given <b>header element</b> at index position <code>0</code> and
	 * optional <b>argument elements</b> at the index positions <code>1..(length-1)</code>. Therefore this expression is not an
	 * <b>atomic expression</b>.
	 * 
	 * @param args
	 *            the arguments of this AST which should be tested, if they are equal, a <code>null</code> value argument skips the
	 *            equals chack.
	 * @see #isAtom()
	 */
	public boolean isAST(IExpr header, int length, IExpr... args);

	/**
	 * Test if this expression is an AST list, where the string representation of the <b>header element</b> at index position
	 * <code>0</code> equals the given <code>symbol</code> and some optional <b>argument elements</b> at the index positions
	 * <code>1..(size()-1)</code>. Therefore this expression is no <b>atomic expression</b>. Example: <code>isAST("Sin")</code>
	 * gives <code>true</code> for <code>Sin[Pi/2]</code>.
	 * 
	 * @see #isAtom()
	 * 
	 */
	public boolean isAST(String symbol);

	/**
	 * Test if this expression is an AST list, where the string representation of the <b>header element</b> at index position
	 * <code>0</code> equals the given <code>symbol</code> and some optional <b>argument elements</b> at the index positions
	 * <code>1..(length-1)</code>. Therefore this expression is no <b>atomic expression</b>. Example: <code>isAST("Sin", 2)</code>
	 * gives <code>true</code> for <code>Sin[0]</code>.
	 * 
	 * @see #isAtom()
	 */
	public boolean isAST(String symbol, int length);

	/**
	 * Test if this expression is an AST list, which contains the given <b>header element</b> at index position <code>0</code> and
	 * optional <b>argument elements</b> at the index positions <code>1..n</code>. <code>n</code> must be greater equal than the
	 * given <code>length</code>. Therefore this expression is no <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 */
	public boolean isASTSizeGE(IExpr header, int length);

	/**
	 * Test if this expression is an atomic expression (i.e. no AST expression)
	 * 
	 */
	public boolean isAtom();

	/**
	 * Test if this expression is a symbolic complex number
	 * 
	 */
	public boolean isComplex();

	/**
	 * Test if this expression is representing ComplexInfinity (i.e. DirectedInfinity[])
	 * 
	 */
	public boolean isComplexInfinity();

	/**
	 * Test if this expression is a numeric complex number
	 * 
	 */
	public boolean isComplexNumeric();

	/**
	 * Test if this expression is the Condition function <code>Condition[&lt;arg1&gt;, &lt;arg2&gt;]</code>
	 * 
	 */
	public boolean isCondition();

	/**
	 * Test if this expression is a symbol with attribute <code>Constant</code>. Therefore numbers return <code>false</code> for
	 * this method!
	 * 
	 * @see #isRealFunction()
	 * @see #isNumericFunction()
	 */
	public boolean isConstant();

	/**
	 * Test if this expression is the function <code>Cos[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isCos();

	/**
	 * Test if this expression is the function <code>Cosh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isCosh();

	/**
	 * Test if this expression is representing a DirectedInfinity (i.e. <code>Infinity->DirectedInfinity[1]</code>,
	 * <code>-Infinity->DirectedInfinity[-1]</code>, <code>ComplexInfinity->DirectedInfinity[]</code>)
	 * 
	 */
	public boolean isDirectedInfinity();

	/**
	 * Test if this expression equals <code>E</code> (base of the natural logarithm; approximately equal to 2.71828...) in symbolic
	 * or numeric mode.
	 * 
	 * <br>
	 * See <a href="http://en.wikipedia.org/wiki/E_%28mathematical_constant%29">e (mathematical constant)</a>
	 * 
	 */
	public boolean isE();

	/**
	 * Test if this expression equals the symbol "False"
	 * 
	 */
	public boolean isFalse();

	/**
	 * Test if this expression is an AST list, which contains a <b>header element</b> (i.e. a function symbol like for example
	 * <code>Dot, Plus or Times</code>) with attribute <code>Flat</code> at index position <code>0</code> and some optional
	 * <b>argument elements</b> at the index positions <code>1..(size()-1)</code>. Examples for <code>Flat</code> functions are
	 * <code>Dot[], Plus[] or Times[]</code>. Therefore this expression is no <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 * 
	 */
	public boolean isFlatAST();

	/**
	 * Test if this expression is a fractional number, but no integer number.
	 * 
	 */
	public boolean isFraction();

	/**
	 * Returns <code>true</code>, if <b>all of the elements</b> in the subexpressions or the expression itself, did not match the
	 * given pattern. Calls <code>isFree(pattern, true)</code>.
	 * 
	 * @param pattern
	 *            a pattern-matching expression
	 * 
	 */
	public boolean isFree(IExpr pattern);

	/**
	 * Returns <code>true</code>, if <b>all of the elements</b> in the subexpressions or the expression itself, did not match the
	 * given pattern.
	 * 
	 * @param pattern
	 *            a pattern-matching expression
	 * @param heads
	 *            if set to <code>false</code>, only the arguments of an IAST should be tested and not the <code>Head[]</code>
	 *            element.
	 * 
	 */
	public boolean isFree(IExpr pattern, boolean heads);

	/**
	 * Returns <code>true</code>, if <b>all of the elements</b> in the subexpressions or the expression itself, did not satisfy the
	 * given unary predicate.
	 * 
	 * @param predicate
	 *            a unary predicate
	 * @param heads
	 *            if set to <code>false</code>, only the arguments of an IAST should be tested and not the <code>Head[]</code>
	 *            element.
	 * 
	 */
	public boolean isFree(Predicate<IExpr> predicate, boolean heads);

	/**
	 * Returns <code>true</code>, if <b>all of the elements</b> in the subexpressions or the expression itself, aren't ASTs with a
	 * head which match the given pattern.
	 * 
	 * @param pattern
	 *            a pattern-matching expression
	 * 
	 */
	public boolean isFreeAST(IExpr pattern);

	/**
	 * Returns <code>true</code>, if <b>all of the elements</b> in the subexpressions or the expression itself, aren't ASTs with a
	 * head which match the given predicate.
	 * 
	 * @param pattern
	 *            a unary predicate
	 * 
	 */
	public boolean isFreeAST(Predicate<IExpr> predicate);

	/**
	 * Test if this expression is a <code>Function( arg1 )</code> expression with at least 1 argument.
	 * 
	 */
	public boolean isFunction();

	/**
	 * Compares this expression with the specified expression for order. Returns true if this expression is canonical greater than
	 * or equal to the specified expression (&lt;= relation).
	 * 
	 * @param expr
	 *            an expression to compare with
	 * @return true if this expression is canonical greater than or equal to the specified expression.
	 */
	public boolean isGEOrdered(IExpr obj);

	/**
	 * Compares this expression with the specified expression for order. Returns true if this expression is canonical greater than
	 * the specified expression (&lt; relation).
	 * 
	 * @param expr
	 *            an expression to compare with
	 * @return true if this expression is canonical greater than the specified expression.
	 */
	public boolean isGTOrdered(IExpr expr);

	/**
	 * Test if this expression is representing <code>Indeterminate</code>
	 * 
	 */
	public boolean isIndeterminate();

	/**
	 * Test if this expression is representing <code>Infinity</code> (i.e. <code>Infinity->DirectedInfinity[1]</code>)
	 * 
	 */
	public boolean isInfinity();

	/**
	 * Test if this expression is a integer number
	 * 
	 */
	public boolean isInteger();

	/**
	 * Compares this expression with the specified expression for order. Returns true if this expression is canonical less than or
	 * equal to the specified expression (&lt;= relation).
	 * 
	 * @param expr
	 *            an expression to compare with
	 * @return true if this expression is canonical less than or equal to the specified expression.
	 */
	public boolean isLEOrdered(IExpr obj);

	/**
	 * Test if this expression is a list (i.e. an AST with head List)
	 * 
	 */
	public boolean isList();

	/**
	 * Test if this expression is a list of lists
	 * 
	 * @see #isList()
	 * @see #isMatrix()
	 * @see #isVector()
	 */
	public boolean isListOfLists();

	/**
	 * Test if this expression is the function <code>Log[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isLog();

	/**
	 * Compares this expression with the specified expression for order. Returns true if this expression is canonical less than the
	 * specified expression (&lt; relation).
	 * 
	 * @param expr
	 *            an expression to compare with
	 * @return true if this expression is canonical less than the specified expression.
	 */
	public boolean isLTOrdered(IExpr expr);

	/**
	 * Test if this expression is a matrix and return the dimensions as array [row-dimension, column-dimension]. This expression is
	 * only a matrix, if all elements are lists with the header <code>List</code> and have the same size.
	 * 
	 * @return <code>null</code> if the expression is not a matrix
	 */
	public int[] isMatrix();

	/**
	 * Returns <code>true</code>, if <b>at least one of the elements</b> in the subexpressions or the expression itself, match the
	 * given pattern.
	 * 
	 * 
	 * @param pattern
	 *            a pattern-matching expression
	 * @param heads
	 *            if set to <code>false</code>, only the arguments of an IAST should be tested and not the <code>Head[]</code>
	 *            element.
	 * 
	 */
	public boolean isMember(IExpr pattern, boolean heads);

	/**
	 * Returns <code>true</code>, if <b>at least one of the elements</b> in the subexpressions or the expression itself, satisfy the
	 * given unary predicate.
	 * 
	 * @param predicate
	 *            a unary predicate
	 * @param heads
	 *            if set to <code>false</code>, only the arguments of an IAST should be tested and not the <code>Head[]</code>
	 *            element.
	 * 
	 */
	public boolean isMember(Predicate<IExpr> predicate, boolean heads);

	/**
	 * Test if this expression equals <code>-1</code> in symbolic or numeric mode.
	 * 
	 */
	public boolean isMinusOne();

	/**
	 * Test if this expression is the Module function <code>Module[&lt;arg1&gt;, &lt;arg2&gt;]</code>
	 * 
	 */
	public boolean isModule();

	/**
	 * Test if this object is a negative signed number.
	 * 
	 * @return <code>true</code>, if <code>this < 0</code>; <code>false</code> in all other case.
	 */
	public boolean isNegative();

	/**
	 * Test if this expression is representing <code>-Infinity</code> (i.e. <code>-Infinity->DirectedInfinity[-1]</code>)
	 * 
	 */
	public boolean isNegativeInfinity();

	/**
	 * Test if this expression is the function <code>Not[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isNot();

	/**
	 * Test if this expression is a number. I.e. an instance of type <code>INumber</code>.
	 * 
	 */
	public boolean isNumber();

	/**
	 * Check if this expression equals an <code>IInteger</code> value. The value of an <code>INum</code> or the value of an
	 * <code>IInteger</code> object can be an <code>IInteger</code> value.
	 * 
	 * @return
	 */
	public boolean isNumEqualInteger(IInteger ii) throws ArithmeticException;

	/**
	 * Test if this expression is a numeric number (i.e. an instance of type <code>INum</code> or type <code>IComplexNum</code>.
	 * 
	 */
	public boolean isNumeric();

	/**
	 * Test if this expression is a numeric function (i.e. a number, a symbolic constant or a function (with attribute
	 * NumericFunction) where all arguments are also &quot;numeric functions&quot;)
	 * 
	 * @return <code>true</code>, if the given expression is a numeric function or value.
	 * @see #isRealFunction
	 */
	public boolean isNumericFunction();

	/**
	 * Test if this expression contains a numeric number (i.e. of type <code>INum</code> or <code>IComplexNum</code>.
	 * 
	 * @return <code>true</code>, if the given expression contains numeric number (i.e. of type <code>INum</code> or
	 *         <code>IComplexNum</code>.
	 * @see #isRealFunction
	 */
	public boolean isNumericMode();

	/**
	 * Check if this expression represents an <code>int</code> value. The value of an <code>INum</code> object can be an
	 * <code>int</code> value.
	 * 
	 * @return
	 */
	public boolean isNumIntValue();

	/**
	 * Test if this expression equals <code>1</code> in symbolic or numeric mode.
	 * 
	 */
	public boolean isOne();

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated use {@link #isOne()} instead.
	 */
	public boolean isONE();

	/**
	 * Test if this expression is the function <code>Or[&lt;arg&gt;,...]</code>
	 * 
	 */
	public boolean isOr();

	/**
	 * Test if this expression is an AST list, which contains a <b>header element</b> (i.e. a function symbol like for example
	 * <code>Plus or Times</code>) with attribute <code>Orderless</code> at index position <code>0</code> and some optional
	 * <b>argument elements</b> at the index positions <code>1..n</code>. Examples for <code>Orderless</code> functions are
	 * <code>Plus[] or Times[]</code>. Therefore this expression is no <b>atomic expression</b>.
	 * 
	 * @see #isAtom()
	 */
	public boolean isOrderlessAST();

	/**
	 * Test if this expression is a pattern object
	 * 
	 */
	public boolean isPattern();

	/**
	 * Return <code>true</code>, if the expression is a pattern with an associated default value (for examle <code>0</code> is the
	 * default value for the addition expression <code>x_+y_.</code>)
	 * 
	 * @return
	 */
	public boolean isPatternDefault();

	/**
	 * Test if this expression or a subexpression is a pattern object. Used in pattern-matching; checks flags in <code>IAST</code>
	 * with flag <code>IAST.CONTAINS_PATTERN_EXPR</code>.
	 * 
	 */
	public boolean isPatternExpr();

	/**
	 * Test if this expression is a pattern sequence object
	 * 
	 */
	public boolean isPatternSequence();

	/**
	 * Test if this expression equals <code>Pi</code> (the ratio of a circle's circumference to its diameter, approx. 3.141592...)
	 * in symbolic or numeric mode.
	 * 
	 * <br>
	 * See <a href="http://en.wikipedia.org/wiki/Pi">Pi</a>
	 * 
	 */
	public boolean isPi();

	/**
	 * Test if this expression is the addition function <code>Plus[&lt;arg1&gt;, &lt;arg2&gt;, ...]</code>
	 * 
	 */
	public boolean isPlus();

	/**
	 * Test if this expression is a polynomial for the given <code>variable</code>.
	 * 
	 */
	public boolean isPolynomial(ISymbol variable);

	/**
	 * Test if this expression is a polynomial for the given list of <code>variables</code>.
	 * 
	 */
	public boolean isPolynomial(IAST variables);

	/**
	 * Test if this expression is a polynomial of <code>maxDegree</code> (i.e. the maximum exponent <= maxDegree) for the given
	 * <code>variable</code>.
	 * 
	 * @param maxDegree
	 *            the maximum degree of the polynomial; maxDegree must be greater 0
	 * 
	 */
	public boolean isPolynomialOfMaxDegree(ISymbol variable, long maxDegree);

	/**
	 * Test if this object is a positive signed number.
	 * 
	 * @return <code>true</code>, if <code>this > 0</code>; <code>false</code> in all other case.
	 */
	public boolean isPositive();

	/**
	 * Test if this expression is the function <code>Power[&lt;arg1&gt;, &lt;arg2&gt;]</code>
	 * 
	 */
	public boolean isPower();

	/**
	 * Test if this expression is a rational number, i.e. integer or fraction number.
	 * 
	 */
	public boolean isRational();

	/**
	 * Test if this expression equals <code>value</code> in symbolic or numeric mode.
	 * 
	 */
	public boolean isRationalValue(IRational value);

	/**
	 * Test if this expression is a real (non-complex) value (i.e. a real number or a real symbolic constant or a
	 * <code>Plus, Times</code> expression with only real values)
	 * 
	 * @return <code>true</code>, if the given expression is a real (non-complex) value.
	 * @see #isConstant
	 * @see #isNumericFunction
	 */
	public boolean isRealFunction();

	/**
	 * Test if this expression is of the form <code>Rule[&lt;arg1&gt;, &lt;arg2&gt;]</code> or
	 * <code>RuleDelayed[&lt;arg1&gt;, &lt;arg2&gt;]</code>.
	 * 
	 */
	public boolean isRuleAST();

	/**
	 * Test if this expression equals the given expression. If the compared expressions are of the same numeric type, they are equal
	 * to a given EPSILON
	 * 
	 */
	public boolean isSame(IExpr expression);

	/**
	 * Test if this expression equals the given expression. If the compared expressions are of the same numeric type, they are equal
	 * to a given EPSILON
	 * 
	 */
	public boolean isSame(IExpr expression, double epsilon);

	/**
	 * Test if this expression is a sequence (i.e. an AST with head Sequence)
	 * 
	 */
	public boolean isSequence();

	/**
	 * Test if this expression is a signed number. I.e. an instance of type <code>ISignedNumber</code>.
	 * 
	 */
	public boolean isSignedNumber();

	/**
	 * Test if this expression is the function <code>Sin[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isSin();

	/**
	 * Test if this expression is the function <code>Sinh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isSinh();

	/**
	 * Test if this expression is the function <code>Slot[&lt;integer-value&gt;]</code>
	 * 
	 */
	public boolean isSlot();

	/**
	 * Test if this expression is the function <code>SlotSequence[&lt;integer-value&gt;]</code>
	 * 
	 */
	public boolean isSlotSequence();

	/**
	 * Test if this expression is a symbol
	 * 
	 */
	public boolean isSymbol();

	/**
	 * Test if this expression is the function <code>TAn[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isTan();

	/**
	 * Test if this expression is the function <code>Tanh[&lt;arg&gt;]</code>
	 * 
	 */
	public boolean isTanh();

	/**
	 * Test if this expression is the multiplication function <code>Times[&lt;arg1&gt;, &lt;arg2&gt;, ...]</code>
	 * 
	 */
	public boolean isTimes();

	/**
	 * Test if this expression equals the symbol <code>True</code>.
	 * 
	 * @return <code>true</code> if the expression equalss symbol <code>True</code> and <code>false</code> in all other cases
	 */
	public boolean isTrue();

	/**
	 * Returns <code>true</code>, if this symbol or ast expression is bound to a value (i.e. the evaluation returns an
	 * <i>assigned</i> value).
	 * 
	 */
	public boolean isValue();

	/**
	 * Test if this expression is a symbol which doesn't has attribute <code>Constant</code>.
	 * 
	 * @see #isConstant()
	 * @see #isSymbol()
	 */
	public boolean isVariable();

	/**
	 * Test if this expression is a vector and return the dimension of the vector. This expression is only a vector, if the
	 * expression is a <code>List(...)</code> and no element is itself a <code>List(...)</code>.
	 * 
	 * @return <code>-1</code> if the expression is no vector or <code>size()-1</code> of this vector AST.
	 */
	public int isVector();

	/**
	 * Test if this expression equals <code>0</code> in symbolic or numeric mode.
	 * 
	 */
	public boolean isZero();

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated use {@link #isZero()} instead.
	 */
	public boolean isZERO();

	/**
	 * Count the number of leaves of this expression.
	 * 
	 * @return
	 */
	public long leafCount();

	/**
	 * Get a list of the leaf expressions.
	 * 
	 * @return Instances of ExprImpl should return null, while any other expression may not return null (but can return an empty
	 *         list).
	 */
	public List<IExpr> leaves();

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this - that)</code>. Calculates
	 * <code>F.eval(F.Plus(this, F.Times(F.CN1, that)))</code> in the common case and uses a specialized implementation for derived
	 * number classes.
	 * 
	 * @param that
	 * @return
	 */
	public IExpr minus(final IExpr that);

	public IExpr mod(final IExpr that);

	/**
	 * Additional multiply method which works like <code>times()</code> to fulfill groovy's method signature
	 * 
	 * @param that
	 * @return
	 * @see IExpr#times(IExpr)
	 */
	@Override
	public IExpr multiply(final IExpr that);

	/**
	 * Additional negative method, which works like opposite to fulfill groovy's method signature
	 * 
	 * @return
	 * @see #opposite()
	 */
	public IExpr negative();

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(-1) * this</code>. Calculates <code>F.eval(F.Times(F.CN1, this))</code>
	 * in the common case and uses a specialized implementation for derived number classes.
	 * 
	 * @param that
	 * @return
	 * @see #negative()
	 */
	public IExpr opposite();

	public IExpr or(final IExpr that);

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this + that)</code>. Calculates <code>F.eval(F.Plus(this, that))</code>
	 * in the common case and uses a specialized implementation for derived number classes.
	 * 
	 * @param that
	 * @return
	 */
	public IExpr plus(final IExpr that);

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this ^ that)</code>. Calculates <code>F.eval(F.Power(this, that))</code>
	 * in the common case and uses a specialized implementation for derived number classes.
	 * 
	 * @param that
	 * @return <code>(this ^ that)</code>
	 */
	public IExpr power(final IExpr that);

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this ^ n)</code>. Calculates <code>F.eval(F.Power(this, that))</code> in
	 * the common case and uses a specialized implementation for derived number classes.
	 * 
	 * @param that
	 * @return <code>(this ^ n)</code>
	 */
	public IExpr power(final long n);

	/**
	 * Replace all (sub-) expressions with the given unary function. If no substitution matches, the method returns
	 * <code>null</code>.
	 * 
	 * @param function
	 *            if the unary functions <code>apply()</code> method returns <code>null</code> the expression isn't substituted.
	 * @return <code>null</code> if no substitution of a (sub-)expression was possible.
	 */
	@Nullable
	public IExpr replaceAll(final Function<IExpr, IExpr> function);

	/**
	 * Replace all (sub-) expressions with the given rule set. If no substitution matches, the method returns <code>null</code>.
	 * 
	 * @param astRules
	 *            rules of the form <code>x-&gt;y</code> or <code>{a-&gt;b, c-&gt;d}</code>; the left-hand-side of the rule can
	 *            contain pattern objects.
	 * @return <code>null</code> if no substitution of a (sub-)expression was possible.
	 */
	@Nullable
	public IExpr replaceAll(final IAST astRules);

	/**
	 * Replace all subexpressions with the given rule set. A rule must contain the position of the subexpression which should be
	 * replaced on the left-hand-side. If no substitution matches, the method returns <code>null</code>.
	 * 
	 * @param astRules
	 *            rules of the form <code>position-&gt;y</code> or <code>{position1-&gt;b, position2-&gt;d}</code>
	 * @return <code>null</code> if no substitution of a subexpression was possible.
	 */
	public IExpr replacePart(final IAST astRules);

	/**
	 * Repeatedly replace all (sub-) expressions with the given unary function. If no substitution matches, the method returns
	 * <code>this</code>.
	 * 
	 * @param function
	 *            if the unary functions <code>apply()</code> method returns <code>null</code> the expression isn't substituted.
	 * @return <code>this</code> if no substitution of a (sub-)expression was possible.
	 */
	public IExpr replaceRepeated(final Function<IExpr, IExpr> function);

	/**
	 * Repeatedly replace all (sub-) expressions with the given rule set. If no substitution matches, the method returns
	 * <code>this</code>.
	 * 
	 * @param astRules
	 *            rules of the form <code>x-&gt;y</code> or <code>{a-&gt;b, c-&gt;d}</code>; the left-hand-side of the rule can
	 *            contain pattern objects.
	 * @return <code>this</code> if no substitution of a (sub-)expression was possible.
	 */
	public IExpr replaceRepeated(final IAST astRules);

	public IExpr replaceSlots(final IAST astSlots);

	/**
	 * Signum functionality is used in JAS toString() method, don't use it as math signum function.
	 * 
	 * @deprecated
	 */
	@Deprecated
	@Override
	public int signum();

	/**
	 * Returns an <code>IExpr</code> whose value is <code>(this * that)</code>. Calculates <code>F.eval(F.Times(this, that))</code>
	 * in the common case and uses a specialized implementation for derived number classes.
	 * 
	 * @param that
	 *            the multiplier expression
	 * @return <code>(this * that)</code>
	 */
	IExpr times(IExpr that);

	/**
	 * The 'highest level' head of the expression, before Symbol, Integer, Real or String. for example while the head of a[b][c] is
	 * a[b], the top head is a.
	 * 
	 * @return the 'highest level' head of the expression.
	 */
	public ISymbol topHead();

	/**
	 * Convert the variables (i.e. expressions of type <code>ISymbol</code> which are'nt constants) in this expression into Slot[]
	 * s.
	 * 
	 * @return <code>null</code> if no variable symbol was found.
	 */
	public IExpr variables2Slots(Map<IExpr, IExpr> map, List<IExpr> variableList);
}
