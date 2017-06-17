package kalkulator

object Expression {
  
  sealed abstract class Expr(val name:String, val value:Int)
  case class Var(override val name:String, override  val value:Int) extends Expr (name, value)
  case class Const(override val name:String, override val value: Int) extends Expr (name, value) 
  
}