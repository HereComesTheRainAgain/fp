package kalkulator

object Expression {
  
  sealed abstract class Expr
  case class Number (n:Int) extends Expr
  case class Sum (e1:Expr, e2:Expr) extends Expr
  case class Var(val name:String, val value:Int) extends Expr
  case class Const(x:String) extends Expr
  case class Prod(e1:Expr, e2:Expr) extends Expr
  
  
  def show(e:Expr) : String = e match {
  	case Number(n) => n.toString
  	
  	case Var(x, y) => x
  	
  	case Sum(e1, e2) => show(e1) + "+" + show(e2)
  	
  	case Prod(Sum(e1, e2), Sum(e3,e4)) =>
  	    "("+show(Sum(e1,e2))+")"+"*"+"("+show(Sum(e3,e4))+")"
  		
  	case Prod(Sum(e1,e2), e3) =>
  	    "("+show(Sum(e1,e2))+")"+"*"+ show(e3)
  		
  	case Prod(e3, Sum(e1,e2)) =>
  	    show(e3) + "*"+"("+show(Sum(e1,e2))+")"
  		
  	case Prod(e1,e2) => show(e1)+"*"+show(e2)
  	
  	case _ => "???"
  }                              
  
}