package kalkulator

class VariableStorage {
  
  private var variables:List[Expression.Var] = List();
  
  def addVariable(name:String, value:Int) = {
    
    if( variables.exists(x => x.name.equals(name)) ) {
      variables = List(Expression.Var(name,value)) ++ reassignVariableValue(variables, name, value)(x => x.name.equals(name))
    } else {
      variables = List(Expression.Var(name,value)) ++ variables
    }
    
  }
  
  def printVarStorage(){
    for (v <- variables) {
      println(v.name, v.value);
    }
  }
  
  
  def findVariable(name:String):Expression.Var = {
    def findVariableIter(variables:List[Expression.Var], name:String):Expression.Var = variables match {
      case Nil => new Expression.Var("!!", -123)
      case x::xs => if (x.name.equals(name))
                        x
                    else
                        findVariableIter(xs, name)
    }
    
    findVariableIter(this.variables, name);
  }
  
  
  def reassignVariableValue (variables:List[Expression.Var], name:String, value:Int)(f: Expression.Var => Boolean)
                : List[Expression.Var] = variables match {
    case Nil => Nil
    case x::xs => if (f(x)) 
                      reassignVariableValue(xs,name,value)(f) 
                    else 
                      x::reassignVariableValue(xs,name,value)(f)
  }
  
}