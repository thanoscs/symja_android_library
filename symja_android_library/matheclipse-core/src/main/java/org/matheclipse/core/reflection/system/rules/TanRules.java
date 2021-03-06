package org.matheclipse.core.reflection.system.rules;

import static org.matheclipse.core.expression.F.*;
import org.matheclipse.core.interfaces.IAST;

/**
 * <p>Generated by <code>org.matheclipse.core.preprocessor.RulePreprocessor</code>.</p>
 * <p>See GIT repository at: <a href="https://bitbucket.org/axelclk/symja_android_library">bitbucket.org/axelclk/symja_android_library under the tools directory</a>.</p>
 */
public interface TanRules {
  /**
   * <ul>
   * <li>index 0 - number of equal rules in <code>RULES</code></li>
	 * </ul>
	 */
  final public static int[] SIZES = { 29, 5 };

  final public static IAST RULES = List(
    IInit(Tan, SIZES),
    // Tan(0)=0
    ISet(Tan(C0),
      C0),
    // Tan(Pi/12)=2-Sqrt(3)
    ISet(Tan(Times(QQ(1L,12L),Pi)),
      Plus(C2,Negate(CSqrt3))),
    // Tan(Pi/10)=Sqrt(1-2/Sqrt(5))
    ISet(Tan(Times(QQ(1L,10L),Pi)),
      Sqrt(Plus(C1,Times(CN2,C1DSqrt5)))),
    // Tan(Pi/8)=-1+Sqrt(2)
    ISet(Tan(Times(QQ(1L,8L),Pi)),
      Plus(CN1,CSqrt2)),
    // Tan(Pi/6)=1/Sqrt(3)
    ISet(Tan(Times(QQ(1L,6L),Pi)),
      C1DSqrt3),
    // Tan(Pi/5)=Sqrt(5-2*Sqrt(5))
    ISet(Tan(Times(QQ(1L,5L),Pi)),
      Sqrt(Plus(C5,Times(CN2,CSqrt5)))),
    // Tan(Pi/4)=1
    ISet(Tan(Times(C1D4,Pi)),
      C1),
    // Tan(3/10*Pi)=Sqrt(1+2/Sqrt(5))
    ISet(Tan(Times(QQ(3L,10L),Pi)),
      Sqrt(Plus(C1,Times(C2,C1DSqrt5)))),
    // Tan(Pi/3)=Sqrt(3)
    ISet(Tan(Times(C1D3,Pi)),
      CSqrt3),
    // Tan(3/8*Pi)=1+Sqrt(2)
    ISet(Tan(Times(QQ(3L,8L),Pi)),
      Plus(C1,CSqrt2)),
    // Tan(2/5*Pi)=Sqrt(5+2*Sqrt(5))
    ISet(Tan(Times(QQ(2L,5L),Pi)),
      Sqrt(Plus(C5,Times(C2,CSqrt5)))),
    // Tan(5/12*Pi)=2+Sqrt(3)
    ISet(Tan(Times(QQ(5L,12L),Pi)),
      Plus(C2,CSqrt3)),
    // Tan(Pi/2)=ComplexInfinity
    ISet(Tan(Times(C1D2,Pi)),
      CComplexInfinity),
    // Tan(7/12*Pi)=-2-Sqrt(3)
    ISet(Tan(Times(QQ(7L,12L),Pi)),
      Plus(CN2,Negate(CSqrt3))),
    // Tan(3/5*Pi)=-Sqrt(5+2*Sqrt(5))
    ISet(Tan(Times(QQ(3L,5L),Pi)),
      Negate(Sqrt(Plus(C5,Times(C2,CSqrt5))))),
    // Tan(5/8*Pi)=-1-Sqrt(2)
    ISet(Tan(Times(QQ(5L,8L),Pi)),
      Plus(CN1,Negate(CSqrt2))),
    // Tan(2/3*Pi)=-Sqrt(3)
    ISet(Tan(Times(QQ(2L,3L),Pi)),
      Negate(CSqrt3)),
    // Tan(7/10*Pi)=-Sqrt(1+2/Sqrt(5))
    ISet(Tan(Times(QQ(7L,10L),Pi)),
      Negate(Sqrt(Plus(C1,Times(C2,C1DSqrt5))))),
    // Tan(3/4*Pi)=-1
    ISet(Tan(Times(QQ(3L,4L),Pi)),
      CN1),
    // Tan(4/5*Pi)=-Sqrt(5-2*Sqrt(5))
    ISet(Tan(Times(QQ(4L,5L),Pi)),
      Negate(Sqrt(Plus(C5,Times(CN2,CSqrt5))))),
    // Tan(5/6*Pi)=-1/Sqrt(3)
    ISet(Tan(Times(QQ(5L,6L),Pi)),
      Negate(C1DSqrt3)),
    // Tan(7/8*Pi)=1-Sqrt(2)
    ISet(Tan(Times(QQ(7L,8L),Pi)),
      Plus(C1,Negate(CSqrt2))),
    // Tan(9/10*Pi)=-Sqrt(1-2/Sqrt(5))
    ISet(Tan(Times(QQ(9L,10L),Pi)),
      Negate(Sqrt(Plus(C1,Times(CN2,C1DSqrt5))))),
    // Tan(11/12*Pi)=-2+Sqrt(3)
    ISet(Tan(Times(QQ(11L,12L),Pi)),
      Plus(CN2,CSqrt3)),
    // Tan(Pi)=0
    ISet(Tan(Pi),
      C0),
    // Tan(I)=I*Tanh(1)
    ISet(Tan(CI),
      Times(CI,Tanh(C1))),
    // Tan(ArcSin(x_)):=x/Sqrt(1-x^2)
    ISetDelayed(Tan(ArcSin(x_)),
      Times(x,Power(Plus(C1,Negate(Sqr(x))),CN1D2))),
    // Tan(Pi*x_NumberQ):=If(x<1,-Tan((1-x)*Pi),If(x<2,Tan((-1+x)*Pi),Tan((x-2*Quotient(IntegerPart(x),2))*Pi)))/;x>1/2
    ISetDelayed(Tan(Times(Pi,$p(x,NumberQ))),
      Condition(If(Less(x,C1),Negate(Tan(Times(Plus(C1,Negate(x)),Pi))),If(Less(x,C2),Tan(Times(Plus(CN1,x),Pi)),Tan(Times(Plus(x,Times(CN2,Quotient(IntegerPart(x),C2))),Pi)))),Greater(x,C1D2))),
    // Tan(ArcTan(x_)):=x
    ISetDelayed(Tan(ArcTan(x_)),
      x),
    // Tan(ArcCos(x_)):=Sqrt(1-x^2)/x
    ISetDelayed(Tan(ArcCos(x_)),
      Times(Sqrt(Plus(C1,Negate(Sqr(x)))),Power(x,-1))),
    // Tan(ArcCot(x_)):=1/x
    ISetDelayed(Tan(ArcCot(x_)),
      Power(x,-1)),
    // Tan(I*Infinity)=I
    ISet(Tan(DirectedInfinity(CI)),
      CI),
    // Tan(-I*Infinity)=-I
    ISet(Tan(DirectedInfinity(CNI)),
      CNI),
    // Tan(ComplexInfinity)=Indeterminate
    ISet(Tan(CComplexInfinity),
      Indeterminate)
  );
}
