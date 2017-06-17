package kalkulator

object Value {
  
  sealed abstract class Val(val name:String, val value:Int)
  case class Var(override val name:String, override  val value:Int) extends Val (name, value)
  case class Const(override val name:String, override val value: Int) extends Val (name, value) 
  
}