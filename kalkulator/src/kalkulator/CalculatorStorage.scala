package kalkulator

class CalculatorStorage {
  
  private var storedValues:List[Expression.Expr] = List();
  
  def addVariable(name:String, value:Int) = {
    
    if( storedValues.exists(x => x.name.equals(name)) ) {
      storedValues = List(Expression.Var(name,value)) ++ dropVariableByName(storedValues, name, value)(x => x.name.equals(name))
    } else {
      storedValues = List(Expression.Var(name,value)) ++ storedValues
    }
    
  }
  
  def addConstant(name:String, value:Int) = {
    
    if( storedValues.exists(x => x.name.equals(name)) ) {
      println(s"Constant $name is already defined and can not be changed!");
    } else {
      storedValues = List(Expression.Const(name,value)) ++ storedValues
    }
    
  }
  
  def printStoredValues(){
    for (v <- storedValues) {
      println(v.name, v.value);
    }
  }
  
  
  def getValueFromStorage(name:String):Option[Expression.Expr]= {
    def findVariableIter(variables:List[Expression.Expr], name:String):Option[Expression.Expr] = variables match {
      case Nil => None
      case x::xs => if (x.name.equals(name))
                        Some(x)
                    else
                        findVariableIter(xs, name)
    }
    
    findVariableIter(this.storedValues, name);
  }
  
  
  def dropVariableByName (variables:List[Expression.Expr], name:String, value:Int)(f: Expression.Expr => Boolean)
                : List[Expression.Expr] = variables match {
    case Nil => Nil
    case x::xs => if (f(x)) 
                      dropVariableByName(xs,name,value)(f) 
                    else 
                      x::dropVariableByName(xs,name,value)(f)
  }
  
}