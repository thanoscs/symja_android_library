package org.matheclipse.core.integrate.rubi45;


import static org.matheclipse.core.expression.F.*;
import static org.matheclipse.core.integrate.rubi45.UtilityFunctionCtors.*;

import org.matheclipse.core.interfaces.IAST;
/** 
 * UtilityFunctions rules from the <a href="http://www.apmaths.uwo.ca/~arich/">Rubi -
 * rule-based integrator</a>.
 *  
 */
public class UtilityFunctions2 { 
  public static IAST RULES = List( 
ISetDelayed(NonnumericFactors(u_),
    If(NumberQ(u),If(ZeroQ(Im(u)),C1,If(ZeroQ(Re(u)),CI,u)),If(PowerQ(u),If(And(RationalQ(Part(u,C1)),FractionQ(Part(u,C2))),Times(u,Power(NumericFactor(u),-1)),u),If(ProductQ(u),Map($s("Integrate::NonnumericFactors"),u),If(SumQ(u),If(Less(LeafCount(u),ZZ(50L)),$(Function(If(SumQ(Slot1),u,NonnumericFactors(Slot1))),ContentFactor(u)),Module(List(Set(n,Apply($s("Integrate::Gcd"),Map($s("Integrate::NumericFactor"),Apply($s("List"),u))))),Map(Function(Times(Slot1,Power(n,-1))),u))),u))))),
ISetDelayed(AbsurdNumberQ(u_),
    RationalQ(u)),
ISetDelayed(AbsurdNumberQ(Power(u_,v_)),
    And(And(RationalQ(u),Greater(u,C0)),FractionQ(v))),
ISetDelayed(AbsurdNumberQ(Times(u_,v_)),
    And(AbsurdNumberQ(u),AbsurdNumberQ(v))),
ISetDelayed(AbsurdNumberFactors(u_),
    If(AbsurdNumberQ(u),u,If(ProductQ(u),Map($s("Integrate::AbsurdNumberFactors"),u),NumericFactor(u)))),
ISetDelayed(NonabsurdNumberFactors(u_),
    If(AbsurdNumberQ(u),C1,If(ProductQ(u),Map($s("Integrate::NonabsurdNumberFactors"),u),NonnumericFactors(u)))),
ISetDelayed(FactorAbsurdNumber(m_),
    If(RationalQ(m),FactorInteger(m),If(PowerQ(m),Map(Function(List(Part(Slot1,C1),Times(Part(Slot1,C2),Part(m,C2)))),FactorInteger(Part(m,C1))),CombineExponents(Sort(Flatten(Map($s("Integrate::FactorAbsurdNumber"),Apply($s("List"),m)),C1),Function(Less(Part(Slot1,C1),Part(Slot2,C1)))))))),
ISetDelayed(CombineExponents($p("lst")),
    If(Less(Length($s("lst")),C2),$s("lst"),If(Equal(Part($s("lst"),C1,C1),Part($s("lst"),C2,C1)),CombineExponents(Prepend(Drop($s("lst"),C2),List(Part($s("lst"),C1,C1),Plus(Part($s("lst"),C1,C2),Part($s("lst"),C2,C2))))),Prepend(CombineExponents(Rest($s("lst"))),First($s("lst")))))),
ISetDelayed(AbsurdNumberGCD($ps("seq")),
    Module(List(Set($s("lst"),List($s("seq")))),If(Equal(Length($s("lst")),C1),First($s("lst")),AbsurdNumberGCDList(FactorAbsurdNumber(First($s("lst"))),FactorAbsurdNumber(Apply($s("Integrate::AbsurdNumberGCD"),Rest($s("lst")))))))),
ISetDelayed(AbsurdNumberGCDList($p("lst1"),$p("lst2")),
    If(SameQ($s("lst1"),List()),Apply(Times,Map(Function(Power(Part(Slot1,C1),Min(Part(Slot1,C2),C0))),$s("lst2"))),If(SameQ($s("lst2"),List()),Apply(Times,Map(Function(Power(Part(Slot1,C1),Min(Part(Slot1,C2),C0))),$s("lst1"))),If(Equal(Part($s("lst1"),C1,C1),Part($s("lst2"),C1,C1)),If(LessEqual(Part($s("lst1"),C1,C2),Part($s("lst2"),C1,C2)),Times(Power(Part($s("lst1"),C1,C1),Part($s("lst1"),C1,C2)),AbsurdNumberGCDList(Rest($s("lst1")),Rest($s("lst2")))),Times(Power(Part($s("lst1"),C1,C1),Part($s("lst2"),C1,C2)),AbsurdNumberGCDList(Rest($s("lst1")),Rest($s("lst2"))))),If(Less(Part($s("lst1"),C1,C1),Part($s("lst2"),C1,C1)),If(Less(Part($s("lst1"),C1,C2),C0),Times(Power(Part($s("lst1"),C1,C1),Part($s("lst1"),C1,C2)),AbsurdNumberGCDList(Rest($s("lst1")),$s("lst2"))),AbsurdNumberGCDList(Rest($s("lst1")),$s("lst2"))),If(Less(Part($s("lst2"),C1,C2),C0),Times(Power(Part($s("lst2"),C1,C1),Part($s("lst2"),C1,C2)),AbsurdNumberGCDList($s("lst1"),Rest($s("lst2")))),AbsurdNumberGCDList($s("lst1"),Rest($s("lst2"))))))))),
ISetDelayed(NormalizeIntegrand(u_,x_Symbol),
    Module(List(Set(v,NormalizeLeadTermSigns(NormalizeIntegrandAux(u,x)))),If(SameQ(v,NormalizeLeadTermSigns(u)),u,v))),
ISetDelayed(NormalizeIntegrandAux(u_,x_Symbol),
    If(SumQ(u),Map(Function(NormalizeIntegrandAux(Slot1,x)),u),If(ProductQ(MergeMonomials(u,x)),Map(Function(NormalizeIntegrandFactor(Slot1,x)),MergeMonomials(u,x)),NormalizeIntegrandFactor(MergeMonomials(u,x),x)))),
ISetDelayed(NormalizeIntegrandFactor(u_,x_Symbol),
    Module(List($s("bas"),$s("deg"),$s("§min")),If(And(PowerQ(u),FreeQ(Part(u,C2),x)),CompoundExpression(CompoundExpression(Set($s("bas"),NormalizeIntegrandFactorBase(Part(u,C1),x)),Set($s("deg"),Part(u,C2))),If(And(IntegerQ($s("deg")),SumQ($s("bas"))),If(MapAnd(Function(MonomialQ(Slot1,x)),$s("bas")),CompoundExpression(Set($s("§min"),MinimumMonomialExponent($s("bas"),x)),Times(Power(x,Times($s("§min"),$s("deg"))),Power(Map(Function(Simplify(Times(Slot1,Power(Power(x,$s("§min")),-1)))),$s("bas")),$s("deg")))),Power($s("bas"),$s("deg"))),Power($s("bas"),$s("deg")))),If(And(PowerQ(u),FreeQ(Part(u,C1),x)),Power(Part(u,C1),NormalizeIntegrandFactorBase(Part(u,C2),x)),CompoundExpression(Set($s("bas"),NormalizeIntegrandFactorBase(u,x)),If(SumQ($s("bas")),If(MapAnd(Function(MonomialQ(Slot1,x)),$s("bas")),CompoundExpression(Set($s("§min"),MinimumMonomialExponent($s("bas"),x)),Times(Power(x,$s("§min")),Map(Function(Times(Slot1,Power(Power(x,$s("§min")),-1))),$s("bas")))),$s("bas")),$s("bas"))))))),
ISetDelayed(NormalizeIntegrandFactorBase(Times(u_,Power(x_,m_DEFAULT)),x_Symbol),
    Condition(NormalizeIntegrandFactorBase(Map(Function(Times(Power(x,m),Slot1)),u),x),And(FreeQ(m,x),SumQ(u)))),
ISetDelayed(NormalizeIntegrandFactorBase(u_,x_Symbol),
    If(BinomialQ(u,x),If(BinomialMatchQ(u,x),u,ExpandToSum(u,x)),If(TrinomialQ(u,x),If(TrinomialMatchQ(u,x),u,ExpandToSum(u,x)),If(ProductQ(u),Map(Function(NormalizeIntegrandFactor(Slot1,x)),u),If(And(PolynomialQ(u,x),LessEqual(Exponent(u,x),C4)),ExpandToSum(u,x),If(SumQ(u),Module(List(Set(v,TogetherSimplify(u))),If(Or(Or(SumQ(v),MatchQ(v,Condition(Times(Power(x,m_DEFAULT),w_),And(FreeQ(m,x),SumQ(w))))),Greater(LeafCount(v),Plus(LeafCount(u),C2))),UnifySum(u,x),NormalizeIntegrandFactorBase(v,x))),Map(Function(NormalizeIntegrandFactor(Slot1,x)),u))))))),
ISetDelayed(NormalizeTogether(u_),
    NormalizeLeadTermSigns(Together(u))),
ISetDelayed(NormalizeLeadTermSigns(u_),
    Module(List(Set($s("lst"),If(ProductQ(u),Map($s("Integrate::SignOfFactor"),u),SignOfFactor(u)))),If(Equal(Part($s("lst"),C1),C1),Part($s("lst"),C2),AbsorbMinusSign(Part($s("lst"),C2))))),
ISetDelayed(AbsorbMinusSign(Times(u_DEFAULT,$p(v,Plus))),
    Times(u,CN1,v)),
ISetDelayed(AbsorbMinusSign(Times(u_DEFAULT,Power($p(v,Plus),m_))),
    Condition(Times(u,Power(Negate(v),m)),OddQ(m))),
ISetDelayed(AbsorbMinusSign(u_),
    Negate(u)),
ISetDelayed(NormalizeSumFactors(u_),
    If(Or(Or(Or(AtomQ(u),SameQ(Head(u),$s("If"))),SameQ(Head(u),$s("Int"))),HeldFormQ(u)),u,If(ProductQ(u),$(Function(Times(Part(Slot1,C1),Part(Slot1,C2))),SignOfFactor(Map($s("Integrate::NormalizeSumFactors"),u))),Map($s("Integrate::NormalizeSumFactors"),u)))),
ISetDelayed(SignOfFactor(u_),
    If(Or(And(RationalQ(u),Less(u,C0)),And(SumQ(u),Less(NumericFactor(First(u)),C0))),List(CN1,Negate(u)),If(And(And(IntegerPowerQ(u),SumQ(Part(u,C1))),Less(NumericFactor(First(Part(u,C1))),C0)),List(Power(CN1,Part(u,C2)),Power(Negate(Part(u,C1)),Part(u,C2))),If(ProductQ(u),Map($s("Integrate::SignOfFactor"),u),List(C1,u))))),
ISetDelayed(NormalizePowerOfLinear(u_,x_Symbol),
    Module(List(Set(v,FactorSquareFree(u))),If(And(And(PowerQ(v),LinearQ(Part(v,C1),x)),FreeQ(Part(v,C2),x)),Power(ExpandToSum(Part(v,C1),x),Part(v,C2)),ExpandToSum(v,x)))),
ISetDelayed(MergeMonomials(Times(u_DEFAULT,Power(Plus(a_DEFAULT,Times(b_DEFAULT,x_)),m_DEFAULT),Power(Plus(c_DEFAULT,Times(d_DEFAULT,x_)),n_DEFAULT)),x_Symbol),
    Condition(Times(u,Power(b,m),Power(Power(d,m),-1),Power(Plus(c,Times(d,x)),Plus(m,n))),And(And(FreeQ(List(a,b,c,d),x),IntegerQ(m)),ZeroQ(Plus(Times(b,c),Times(CN1,a,d)))))),
ISetDelayed(MergeMonomials(Times(u_DEFAULT,Power(Plus(a_DEFAULT,Times(b_DEFAULT,x_)),m_DEFAULT),Power(Times(c_DEFAULT,Power(Plus(a_DEFAULT,Times(b_DEFAULT,x_)),n_DEFAULT)),p_)),x_Symbol),
    Condition(Times(u,Power(Times(c,Power(Plus(a,Times(b,x)),n)),Plus(Times(m,Power(n,-1)),p)),Power(Power(c,Times(m,Power(n,-1))),-1)),And(FreeQ(List(a,b,c,m,n,p),x),IntegerQ(Times(m,Power(n,-1)))))),
ISetDelayed(MergeMonomials(u_,x_Symbol),
    u),
ISetDelayed(SimplifyIntegrand(u_,x_Symbol),
    Module(List(v),CompoundExpression(Set(v,NormalizeLeadTermSigns(NormalizeIntegrandAux(Simplify(u),x))),If(Less(LeafCount(v),Times(QQ(4L,5L),LeafCount(u))),v,If(UnsameQ(v,NormalizeLeadTermSigns(u)),v,u))))),
ISetDelayed(SimplifyTerm(u_,x_Symbol),
    Module(List(Set(v,Simplify(u)),w),CompoundExpression(Set(w,Together(v)),NormalizeIntegrand(If(Less(LeafCount(v),LeafCount(w)),w,w),x)))),
ISetDelayed(TogetherSimplify(u_),
    TimeConstrained(Module(List(v),CompoundExpression(Set(v,Together(Simplify(Together(u)))),TimeConstrained(FixSimplify(v),Times(C1D3,$s("§timelimit")),v))),$s("§timelimit"),u)),
ISetDelayed(SmartSimplify(u_),
    TimeConstrained(Module(List(v,w),CompoundExpression(CompoundExpression(CompoundExpression(CompoundExpression(CompoundExpression(Set(v,Simplify(u)),Set(w,Factor(v))),Set(v,If(Less(LeafCount(w),LeafCount(v)),w,v))),Set(v,If(And(NotFalseQ(Set(w,FractionalPowerOfSquareQ(v))),FractionalPowerSubexpressionQ(u,w,Expand(w))),SubstForExpn(v,w,Expand(w)),v))),Set(v,FactorNumericGcd(v))),TimeConstrained(FixSimplify(v),Times(C1D3,$s("§timelimit")),v))),$s("§timelimit"),u)),
ISetDelayed(SubstForExpn(u_,v_,w_),
    If(SameQ(u,v),w,If(AtomQ(u),u,Map(Function(SubstForExpn(Slot1,v,w)),u)))),
ISetDelayed(Simp(u_,x_),
    TimeConstrained(NormalizeSumFactors(SimpHelp(u,x)),$s("§timelimit"),u)),
ISetDelayed(SimpHelp(Power(E,Times(u_DEFAULT,Plus(w_,Times(v_DEFAULT,Log(a_))))),x_),
    Times(Power(a,Times(u,v)),SimpHelp(Power(E,Times(u,w)),x))),
ISetDelayed(SimpHelp(u_,x_),
    If(AtomQ(u),u,If(Or(Or(SameQ(Head(u),$s("If")),SameQ(Head(u),$s("Int"))),HeldFormQ(u)),u,If(FreeQ(u,x),Module(List(Set(v,SmartSimplify(u))),If(LessEqual(LeafCount(v),LeafCount(u)),v,u)),If(ProductQ(u),Module(List(Set(v,FreeFactors(u,x)),Set(w,NonfreeFactors(u,x))),CompoundExpression(CompoundExpression(CompoundExpression(CompoundExpression(Set(v,Times(NumericFactor(v),SmartSimplify(Times(NonnumericFactors(v),Sqr(x))),Power(x,-2))),Set(w,If(ProductQ(w),Map(Function(SimpHelp(Slot1,x)),w),SimpHelp(w,x)))),Set(w,FactorNumericGcd(w))),Set(v,MergeFactors(v,w))),If(ProductQ(v),Map(Function(SimpFixFactor(Slot1,x)),v),v))),If(SumQ(u),If(And(PolynomialQ(u,x),LessEqual(Exponent(u,x),C0)),SimpHelp(Coefficient(u,x,C0),x),If(And(And(PolynomialQ(u,x),Equal(Exponent(u,x),C1)),SameQ(Coefficient(u,x,C0),C0)),Times(SimpHelp(Coefficient(u,x,C1),x),x),Module(List(Set(v,C0),Set(w,C0)),CompoundExpression(CompoundExpression(CompoundExpression(Scan(Function(If(FreeQ(Slot1,x),Set(v,Plus(Slot1,v)),Set(w,Plus(Slot1,w)))),u),Set(v,SmartSimplify(v))),Set(w,If(SumQ(w),Map(Function(SimpHelp(Slot1,x)),w),SimpHelp(w,x)))),Plus(v,w))))),Map(Function(SimpHelp(Slot1,x)),u))))))),
ISetDelayed(FractionalPowerOfSquareQ(u_),
    If(AtomQ(u),False,If(And(FractionalPowerQ(u),MatchQ(Part(u,C1),Condition(Times(a_DEFAULT,Sqr(Plus(b_,c_))),NonsumQ(a)))),Part(u,C1),Module(List($s("tmp")),Catch(CompoundExpression(Scan(Function(If(NotFalseQ(Set($s("tmp"),FractionalPowerOfSquareQ(Slot1))),Throw($s("tmp")))),u),False)))))),
ISetDelayed(FractionalPowerSubexpressionQ(u_,v_,w_),
    If(AtomQ(u),False,If(And(FractionalPowerQ(u),PositiveQ(Times(Part(u,C1),Power(w,-1)))),And(Not(SameQ(Part(u,C1),v)),Less(LeafCount(w),Times(C3,LeafCount(v)))),Catch(CompoundExpression(Scan(Function(If(FractionalPowerSubexpressionQ(Slot1,v,w),Throw(True))),u),False))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(Plus(w_,Times(v_DEFAULT,Complex(C0,b_))),n_DEFAULT),Complex(C0,a_))),
    Condition(Times(Power(CN1,Times(C1D2,Plus(n,C1))),a,u,FixSimplify(Power(Plus(Times(b,v),Times(CN1,CI,w)),n))),OddQ(n))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(u_,m_DEFAULT),Power(v_,n_))),
    Condition(Module(List(Set(z,Simplify(Times(Power(u,Times(m,Power(GCD(m,n),-1))),Power(v,Times(n,Power(GCD(m,n),-1))))))),Condition(FixSimplify(Times(w,Power(z,GCD(m,n)))),Or(AbsurdNumberQ(z),SqrtNumberSumQ(z)))),And(And(And(And(And(RationalQ(m),FractionQ(n)),SqrtNumberSumQ(u)),SqrtNumberSumQ(v)),PositiveQ(u)),PositiveQ(v)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(u_,m_DEFAULT),Power(v_,n_))),
    Condition(Module(List(Set(z,Simplify(Times(Power(u,Times(m,Power(GCD(m,Negate(n)),-1))),Power(v,Times(n,Power(GCD(m,Negate(n)),-1))))))),Condition(FixSimplify(Times(w,Power(z,GCD(m,Negate(n))))),Or(AbsurdNumberQ(z),SqrtNumberSumQ(z)))),And(And(And(And(And(RationalQ(m),FractionQ(n)),SqrtNumberSumQ(u)),SqrtNumberSumQ(Power(v,-1))),PositiveQ(u)),PositiveQ(v)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(u_,m_DEFAULT),Power(v_,n_))),
    Condition(Module(List(Set(z,Simplify(Times(Power(Negate(u),Times(m,Power(GCD(m,n),-1))),Power(v,Times(n,Power(GCD(m,n),-1))))))),Condition(FixSimplify(Times(CN1,w,Power(z,GCD(m,n)))),Or(AbsurdNumberQ(z),SqrtNumberSumQ(z)))),And(And(And(And(And(IntegerQ(m),FractionQ(n)),SqrtNumberSumQ(u)),SqrtNumberSumQ(v)),NegativeQ(u)),PositiveQ(v)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(u_,m_DEFAULT),Power(v_,n_))),
    Condition(Module(List(Set(z,Simplify(Times(Power(Negate(u),Times(m,Power(GCD(m,Negate(n)),-1))),Power(v,Times(n,Power(GCD(m,Negate(n)),-1))))))),Condition(FixSimplify(Times(CN1,w,Power(z,GCD(m,Negate(n))))),Or(AbsurdNumberQ(z),SqrtNumberSumQ(z)))),And(And(And(And(And(IntegerQ(m),FractionQ(n)),SqrtNumberSumQ(u)),SqrtNumberSumQ(Power(v,-1))),NegativeQ(u)),PositiveQ(v)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(a_,m_),Power(Plus(u_,Times(v_DEFAULT,Power(b_,n_))),p_DEFAULT))),
    Condition(Module(List(Set(c,Simplify(Times(Power(a,Times(m,Power(p,-1))),Power(b,n))))),Condition(FixSimplify(Times(w,Power(Plus(Times(Power(a,Times(m,Power(p,-1))),u),Times(c,v)),p))),RationalQ(c))),And(And(And(RationalQ(a,b,m,n),Greater(a,C0)),Greater(b,C0)),PositiveIntegerQ(p)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Plus(Times(u_DEFAULT,Power(a_,n_)),Times(v_DEFAULT,Power(b_,p_DEFAULT))),Power(a_,m_DEFAULT))),
    Condition(FixSimplify(Times(w,Power(a,Plus(m,n)),Plus(u,Times(Power(CN1,p),Power(a,Plus(p,Negate(n))),v)))),And(And(And(And(RationalQ(m),FractionQ(n)),IntegerQ(p)),Greater(Plus(p,Negate(n)),C0)),SameQ(Plus(a,b),C0)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(Plus(a_,b_),m_DEFAULT),Power(Plus(c_,d_),n_))),
    Condition(Module(List(Set(q,Simplify(Times(b,Power(d,-1))))),Condition(FixSimplify(Times(w,Power(q,m),Power(Plus(c,d),Plus(m,n)))),FreeQ(q,Plus))),And(And(IntegerQ(m),Not(IntegerQ(n))),ZeroQ(Plus(Times(b,c),Times(CN1,a,d)))))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(Plus(Times(u_DEFAULT,Power(a_,m_DEFAULT)),Times(v_DEFAULT,Power(a_,n_DEFAULT))),t_DEFAULT))),
    Condition(FixSimplify(Times(Power(a,Times(m,t)),w,Power(Plus(u,Times(Power(a,Plus(n,Negate(m))),v)),t))),And(And(And(Not(RationalQ(a)),IntegerQ(t)),RationalQ(m,n)),And(Less(C0,m),LessEqual(m,n))))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(Plus(Times(u_DEFAULT,Power(a_,m_DEFAULT)),Times(v_DEFAULT,Power(a_,n_DEFAULT)),Times(z_DEFAULT,Power(a_,p_DEFAULT))),t_DEFAULT))),
    Condition(FixSimplify(Times(Power(a,Times(m,t)),w,Power(Plus(u,Times(Power(a,Plus(n,Negate(m))),v),Times(Power(a,Plus(p,Negate(m))),z)),t))),And(And(And(Not(RationalQ(a)),IntegerQ(t)),RationalQ(m,n,p)),LessEqual(And(Less(C0,m),LessEqual(m,n)),p)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Power(Plus(Times(u_DEFAULT,Power(a_,m_DEFAULT)),Times(v_DEFAULT,Power(a_,n_DEFAULT)),Times(z_DEFAULT,Power(a_,p_DEFAULT)),Times(y_DEFAULT,Power(a_,q_DEFAULT))),t_DEFAULT))),
    Condition(FixSimplify(Times(Power(a,Times(m,t)),w,Power(Plus(u,Times(Power(a,Plus(n,Negate(m))),v),Times(Power(a,Plus(p,Negate(m))),z),Times(Power(a,Plus(q,Negate(m))),y)),t))),And(And(And(Not(RationalQ(a)),IntegerQ(t)),RationalQ(m,n,p)),LessEqual(LessEqual(And(Less(C0,m),LessEqual(m,n)),p),q)))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Plus(u_DEFAULT,Times(b_DEFAULT,Sqrt(v_)),Times(c_DEFAULT,Sqrt(v_)),Times(d_DEFAULT,Sqrt(v_)),Times(a_DEFAULT,Sqrt($p(v,Plus)))))),
    FixSimplify(Times(w,Plus(u,Times(FixSimplify(Plus(a,b,c,d)),Sqrt(v)))))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Plus(u_DEFAULT,Times(b_DEFAULT,Sqrt(v_)),Times(c_DEFAULT,Sqrt(v_)),Times(a_DEFAULT,Sqrt($p(v,Plus)))))),
    FixSimplify(Times(w,Plus(u,Times(FixSimplify(Plus(a,b,c)),Sqrt(v)))))),
ISetDelayed(FixSimplify(Times(w_DEFAULT,Plus(u_DEFAULT,Times(b_DEFAULT,Sqrt(v_)),Times(a_DEFAULT,Sqrt($p(v,Plus)))))),
    FixSimplify(Times(w,Plus(u,Times(FixSimplify(Plus(a,b)),Sqrt(v)))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(a_,m_),Sqrt(Times(b_DEFAULT,Plus(c_,Times(d_DEFAULT,Sqrt(a_))))))),
    Condition(Times(Sqrt(Together(Times(b,Plus(Times(c,Power(a,Times(C2,m))),Times(d,Power(a,Plus(Times(C2,m),C1D2))))))),FixSimplify(u)),And(And(RationalQ(a,b,c,d,m),Greater(a,C0)),Equal(Denominator(m),C4)))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(a_,m_),Power(Times(b_DEFAULT,Plus(c_,Times(d_DEFAULT,Sqrt(a_)))),CN1D2))),
    Condition(Times(FixSimplify(u),Power(Together(Times(b,Plus(Times(c,Power(Power(a,Times(C2,m)),-1)),Times(d,Power(Power(a,Plus(Times(C2,m),Negate(C1D2))),-1))))),CN1D2)),And(And(RationalQ(a,b,c,d,m),Greater(a,C0)),Equal(Denominator(m),C4)))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(v_,m_),Power(w_,n_))),
    Condition(Negate(FixSimplify(Times(u,Power(v,Plus(m,Negate(C1)))))),And(And(And(And(RationalQ(m),Not(RationalQ(w))),FractionQ(n)),Less(n,C0)),ZeroQ(Plus(v,Power(w,Negate(n))))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(v_,m_),Power(w_,n_DEFAULT))),
    Condition(Times(Power(CN1,n),FixSimplify(Times(u,Power(v,Plus(m,n))))),And(And(And(RationalQ(m),Not(RationalQ(w))),IntegerQ(n)),ZeroQ(Plus(v,w))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(w_,n_DEFAULT),Power(Negate(Power(v_,p_DEFAULT)),m_))),
    Condition(Times(Power(CN1,Times(n,Power(p,-1))),FixSimplify(Times(u,Power(Negate(Power(v,p)),Plus(m,Times(n,Power(p,-1))))))),And(And(And(RationalQ(m),Not(RationalQ(w))),IntegerQ(Times(n,Power(p,-1)))),ZeroQ(Plus(v,Negate(w)))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(w_,n_DEFAULT),Power(Negate(Power(v_,p_DEFAULT)),m_))),
    Condition(Times(Power(CN1,Plus(n,Times(n,Power(p,-1)))),FixSimplify(Times(u,Power(Negate(Power(v,p)),Plus(m,Times(n,Power(p,-1))))))),And(And(And(RationalQ(m),Not(RationalQ(w))),IntegersQ(n,Times(n,Power(p,-1)))),ZeroQ(Plus(v,w))))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(Plus(a,b),m_DEFAULT),Power(Plus(a,Negate(b)),m_DEFAULT))),
    Condition(Times(u,Power(Plus(Sqr(a),Negate(Sqr(b))),m)),IntegerQ(m))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(Plus(Times(c,Sqr(d)),Times(Plus(Times(CN1,b,d),Times(CN1,CN1,a,e)),e)),m_DEFAULT))),
    Condition(Times(u,Power(Plus(Times(c,Sqr(d)),Times(CN1,b,d,e),Times(a,Sqr(e))),m)),RationalQ(m))),
ISetDelayed(FixSimplify(Times(u_DEFAULT,Power(Plus(Times(c,Sqr(d)),Times(Plus(Times(CN1,b,d),Times(a,e)),e)),m_DEFAULT))),
    Condition(Times(u,Power(Plus(Times(c,Sqr(d)),Times(CN1,b,d,e),Times(a,Sqr(e))),m)),RationalQ(m))),
ISetDelayed(FixSimplify(u_),
    u),
ISetDelayed(SimpFixFactor(Power(Plus(Times(a_DEFAULT,Complex(C0,c_)),Times(b_DEFAULT,Complex(C0,d_))),p_DEFAULT),x_),
    Condition(Times(Power(CI,p),SimpFixFactor(Power(Plus(Times(a,c),Times(b,d)),p),x)),IntegerQ(p))),
ISetDelayed(SimpFixFactor(Power(Plus(Times(a_DEFAULT,Complex(C0,d_)),Times(b_DEFAULT,Complex(C0,e_)),Times(c_DEFAULT,Complex(C0,f_))),p_DEFAULT),x_),
    Condition(Times(Power(CI,p),SimpFixFactor(Power(Plus(Times(a,d),Times(b,e),Times(c,f)),p),x)),IntegerQ(p))),
ISetDelayed(SimpFixFactor(Power(Plus(Times(a_DEFAULT,Power(c_,r_)),Times(b_DEFAULT,Power(x_,n_DEFAULT))),p_DEFAULT),x_),
    Condition(Times(Power(c,Times(r,p)),SimpFixFactor(Power(Plus(a,Times(b,Power(Power(c,r),-1),Power(x,n))),p),x)),And(And(And(And(FreeQ(List(a,b,c),x),IntegersQ(n,p)),AtomQ(c)),RationalQ(r)),Less(r,C0)))),
ISetDelayed(SimpFixFactor(Power(Plus(a_DEFAULT,Times(b_DEFAULT,Power(c_,r_),Power(x_,n_DEFAULT))),p_DEFAULT),x_),
    Condition(Times(Power(c,Times(r,p)),SimpFixFactor(Power(Plus(Times(a,Power(Power(c,r),-1)),Times(b,Power(x,n))),p),x)),And(And(And(And(FreeQ(List(a,b,c),x),IntegersQ(n,p)),AtomQ(c)),RationalQ(r)),Less(r,C0)))),
ISetDelayed(SimpFixFactor(Power(Plus(Times(a_DEFAULT,Power(c_,s_DEFAULT)),Times(b_DEFAULT,Power(c_,r_DEFAULT),Power(x_,n_DEFAULT))),p_DEFAULT),x_),
    Condition(Times(Power(c,Times(s,p)),SimpFixFactor(Power(Plus(a,Times(b,Power(c,Plus(r,Negate(s))),Power(x,n))),p),x)),And(And(And(And(FreeQ(List(a,b,c),x),IntegersQ(n,p)),RationalQ(s,r)),And(Less(C0,s),LessEqual(s,r))),UnsameQ(Power(c,Times(s,p)),CN1)))),
ISetDelayed(SimpFixFactor(Power(Plus(Times(a_DEFAULT,Power(c_,s_DEFAULT)),Times(b_DEFAULT,Power(c_,r_DEFAULT),Power(x_,n_DEFAULT))),p_DEFAULT),x_),
    Condition(Times(Power(c,Times(r,p)),SimpFixFactor(Power(Plus(Times(a,Power(c,Plus(s,Negate(r)))),Times(b,Power(x,n))),p),x)),And(And(And(And(FreeQ(List(a,b,c),x),IntegersQ(n,p)),RationalQ(s,r)),Less(Less(C0,r),s)),UnsameQ(Power(c,Times(r,p)),CN1)))),
ISetDelayed(SimpFixFactor(u_,x_),
    u),
ISetDelayed(FactorNumericGcd(u_),
    If(And(PowerQ(u),RationalQ(Part(u,C2))),Power(FactorNumericGcd(Part(u,C1)),Part(u,C2)),If(ProductQ(u),Map($s("Integrate::FactorNumericGcd"),u),If(SumQ(u),Module(List(Set(g,Apply($s("GCD"),Map($s("Integrate::NumericFactor"),Apply($s("List"),u))))),Times(g,Map(Function(Times(Slot1,Power(g,-1))),u))),u)))),
ISetDelayed(MergeFactors(u_,v_),
    If(ProductQ(u),MergeFactors(Rest(u),MergeFactors(First(u),v)),If(PowerQ(u),If(MergeableFactorQ(Part(u,C1),Part(u,C2),v),MergeFactor(Part(u,C1),Part(u,C2),v),If(And(And(RationalQ(Part(u,C2)),Less(Part(u,C2),CN1)),MergeableFactorQ(Part(u,C1),CN1,v)),MergeFactors(Power(Part(u,C1),Plus(Part(u,C2),C1)),MergeFactor(Part(u,C1),CN1,v)),Times(u,v))),If(MergeableFactorQ(u,C1,v),MergeFactor(u,C1,v),Times(u,v))))),
ISetDelayed(MergeFactor($p("bas"),$p("deg"),v_),
    If(SameQ($s("bas"),v),Power($s("bas"),Plus($s("deg"),C1)),If(PowerQ(v),If(SameQ($s("bas"),Part(v,C1)),Power($s("bas"),Plus($s("deg"),Part(v,C2))),Power(MergeFactor($s("bas"),Times($s("deg"),Power(Part(v,C2),-1)),Part(v,C1)),Part(v,C2))),If(ProductQ(v),If(MergeableFactorQ($s("bas"),$s("deg"),First(v)),Times(MergeFactor($s("bas"),$s("deg"),First(v)),Rest(v)),Times(First(v),MergeFactor($s("bas"),$s("deg"),Rest(v)))),Plus(MergeFactor($s("bas"),$s("deg"),First(v)),MergeFactor($s("bas"),$s("deg"),Rest(v))))))),
ISetDelayed(MergeableFactorQ($p("bas"),$p("deg"),v_),
    If(SameQ($s("bas"),v),And(RationalQ(Plus($s("deg"),C1)),Or(GreaterEqual(Plus($s("deg"),C1),C0),And(RationalQ($s("deg")),Greater($s("deg"),C0)))),If(PowerQ(v),If(SameQ($s("bas"),Part(v,C1)),And(RationalQ(Plus($s("deg"),Part(v,C2))),Or(GreaterEqual(Plus($s("deg"),Part(v,C2)),C0),And(RationalQ($s("deg")),Greater($s("deg"),C0)))),And(And(And(SumQ(Part(v,C1)),IntegerQ(Part(v,C2))),Or(Not(IntegerQ($s("deg"))),IntegerQ(Times($s("deg"),Power(Part(v,C2),-1))))),MergeableFactorQ($s("bas"),Times($s("deg"),Power(Part(v,C2),-1)),Part(v,C1)))),If(ProductQ(v),Or(MergeableFactorQ($s("bas"),$s("deg"),First(v)),MergeableFactorQ($s("bas"),$s("deg"),Rest(v))),And(And(SumQ(v),MergeableFactorQ($s("bas"),$s("deg"),First(v))),MergeableFactorQ($s("bas"),$s("deg"),Rest(v))))))),
ISetDelayed(TrigSimplifyQ(u_),
    UnsameQ(ActivateTrig(u),TrigSimplify(u))),
ISetDelayed(TrigSimplify(u_),
    ActivateTrig(TrigSimplifyRecur(u))),
ISetDelayed(TrigSimplifyRecur(u_),
    If(AtomQ(u),u,If(SameQ(Head(u),$s("If")),u,TrigSimplifyAux(Map($s("Integrate::TrigSimplifyRecur"),u))))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(Times(a_DEFAULT,Power(v_,m_DEFAULT)),Times(b_DEFAULT,Power(v_,n_DEFAULT))),p_))),
    Condition(Times(u,Power(v,Times(m,p)),Power(TrigSimplifyAux(Plus(a,Times(b,Power(v,Plus(n,Negate(m)))))),p)),And(And(And(InertTrigQ(v),IntegerQ(p)),RationalQ(m,n)),Less(m,n)))),
ISetDelayed(TrigSimplifyAux(Plus(v_DEFAULT,Times(a_DEFAULT,Sqr($($s("§cos"),u_))),Times(b_DEFAULT,Sqr($($s("§sin"),u_))))),
    Condition(Plus(a,v),SameQ(a,b))),
ISetDelayed(TrigSimplifyAux(Plus(v_DEFAULT,Times(a_DEFAULT,Sqr($($s("§sec"),u_))),Times(b_DEFAULT,Sqr($($s("§tan"),u_))))),
    Condition(Plus(a,v),SameQ(a,Negate(b)))),
ISetDelayed(TrigSimplifyAux(Plus(v_DEFAULT,Times(b_DEFAULT,Sqr($($s("§cot"),u_))),Times(a_DEFAULT,Sqr($($s("§csc"),u_))))),
    Condition(Plus(a,v),SameQ(a,Negate(b)))),
ISetDelayed(TrigSimplifyAux(Power(Plus(v_DEFAULT,Times(a_DEFAULT,Sqr($($s("§cos"),u_))),Times(b_DEFAULT,Sqr($($s("§sin"),u_)))),n_)),
    Power(Plus(Times(Plus(b,Negate(a)),Sqr(Sin(u))),a,v),n)),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§sin"),z_))))),
    Condition(Plus(Times(u,Sqr(Cos(z))),w),SameQ(u,Negate(v)))),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§cos"),z_))))),
    Condition(Plus(Times(u,Sqr(Sin(z))),w),SameQ(u,Negate(v)))),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§tan"),z_))))),
    Condition(Plus(Times(u,Sqr(Sec(z))),w),SameQ(u,v))),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§cot"),z_))))),
    Condition(Plus(Times(u,Sqr(Csc(z))),w),SameQ(u,v))),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§sec"),z_))))),
    Condition(Plus(Times(v,Sqr(Tan(z))),w),SameQ(u,Negate(v)))),
ISetDelayed(TrigSimplifyAux(Plus(u_,w_DEFAULT,Times(v_DEFAULT,Sqr($($s("§csc"),z_))))),
    Condition(Plus(Times(v,Sqr(Cot(z))),w),SameQ(u,Negate(v)))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§cos"),v_))),-1),Sqr($($s("§sin"),v_)))),
    Condition(Times(u,Plus(Power(a,-1),Times(CN1,Cos(v),Power(b,-1)))),ZeroQ(Plus(Sqr(a),Negate(Sqr(b)))))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§sin"),v_))),-1),Sqr($($s("§cos"),v_)))),
    Condition(Times(u,Plus(Power(a,-1),Times(CN1,Sin(v),Power(b,-1)))),ZeroQ(Plus(Sqr(a),Negate(Sqr(b)))))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,Power($($s("§tan"),v_),n_DEFAULT))),-1),Power($($s("§tan"),v_),n_DEFAULT))),
    Condition(Times(u,Power(Plus(b,Times(a,Power(Cot(v),n))),-1)),And(PositiveIntegerQ(n),NonsumQ(a)))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,Power($($s("§cot"),v_),n_DEFAULT))),-1),Power($($s("§cot"),v_),n_DEFAULT))),
    Condition(Times(u,Power(Plus(b,Times(a,Power(Tan(v),n))),-1)),And(PositiveIntegerQ(n),NonsumQ(a)))),
ISetDelayed(TrigSimplifyAux(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,Power($($s("§sec"),v_),n_DEFAULT))),-1),Power($($s("§sec"),v_),n_DEFAULT))),
    Condition(Times(u,Power(Plus(b,Times(a,Power(Cos(v),n))),-1)),And(PositiveIntegerQ(n),NonsumQ(a))))
  );
}
