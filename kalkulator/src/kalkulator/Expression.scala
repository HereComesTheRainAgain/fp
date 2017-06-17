package kalkulator

object Expression {
  
  sealed abstract class Expr
  case class Var(val name:String, val value:Int) extends Expr
  case class Const(val name:String, val value: Int) extends Expr  
  
}