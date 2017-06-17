package kalkulator

class CalculatorStorage {
  
  private var storedValues:List[Value.Val] = List();
  
  def addVariable(name:String, value:Int) = {
    
    if( storedValues.exists(x => x.name.equals(name)) ) {
      storedValues = List(Value.Var(name,value)) ++ dropVariableByName(storedValues, name, value)(x => x.name.equals(name))
    } else {
      storedValues = List(Value.Var(name,value)) ++ storedValues
    }
    
  }
  
  def addConstant(name:String, value:Int) = {
    
    if( storedValues.exists(x => x.name.equals(name)) ) {
      println(s"Constant $name is already defined and can not be changed!");
    } else {
      storedValues = List(Value.Const(name,value)) ++ storedValues
    }
    
  }
  
  def printStoredValues(){
    for (v <- storedValues) {
      println(v.name, v.value);
    }
  }
  
  
  def getValueFromStorage(name:String):Option[Value.Val]= {
    def findVariableIter(variables:List[Value.Val], name:String):Option[Value.Val] = variables match {
      case Nil => None
      case x::xs => if (x.name.equals(name))
                        Some(x)
                    else
                        findVariableIter(xs, name)
    }
    
    findVariableIter(this.storedValues, name);
  }
  
  
  def dropVariableByName (variables:List[Value.Val], name:String, value:Int)(f: Value.Val => Boolean)
                : List[Value.Val] = variables match {
    case Nil => Nil
    case x::xs => if (f(x)) 
                      dropVariableByName(xs,name,value)(f) 
                    else 
                      x::dropVariableByName(xs,name,value)(f)
  }
  
}