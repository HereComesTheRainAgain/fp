package kalkulator

class VariableStorage {
  
  private var variables:List[Expression.Var] = List();
  
  def addVariable(name:String, value:Int) = {
    
    if( variables.exists(x => x.name.equals(name)) ) {
      variables = List(Expression.Var(name,value)) ++ dropVariableByName(variables, name, value)(x => x.name.equals(name))
    } else {
      variables = List(Expression.Var(name,value)) ++ variables
    }
    
  }
  
  def printVarStorage(){
    for (v <- variables) {
      println(v.name, v.value);
    }
  }
  
  
  def findVariable(name:String):Option[Expression.Var]= {
    def findVariableIter(variables:List[Expression.Var], name:String):Option[Expression.Var] = variables match {
      case Nil => None
      case x::xs => if (x.name.equals(name))
                        Some(x)
                    else
                        findVariableIter(xs, name)
    }
    
    findVariableIter(this.variables, name);
  }
  
  
  def dropVariableByName (variables:List[Expression.Var], name:String, value:Int)(f: Expression.Var => Boolean)
                : List[Expression.Var] = variables match {
    case Nil => Nil
    case x::xs => if (f(x)) 
                      dropVariableByName(xs,name,value)(f) 
                    else 
                      x::dropVariableByName(xs,name,value)(f)
  }
  
}