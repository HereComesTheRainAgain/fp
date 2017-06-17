package kalkulator

class InputStreamEvaluator(var variableStorage:VariableStorage, var calculator:Calculator) {
  
  def evaluate (input: String) = input match {
    
    case store if store.startsWith("store") => 
      var result = store.split(" ");
      variableStorage.addVariable(result(1), result(2).toInt);
      variableStorage.printVarStorage();
    
    case operation if operation.contains(" + ") || operation.contains(" / ") 
                      || operation.contains(" - ") || operation.contains(" * ") || operation.contains(" ^ ") =>
      var result = operation.split(" ");
      
      var e1 = new Expression.Var("", -1);
      var e2 = new Expression.Var("", -1);
      
      if(Character.isDigit(result(0).charAt(0)) ) 
        e1 = new Expression.Var("", result(0).toInt);
      else 
        e1 = variableStorage.findVariable(result(0));
      
      if(Character.isDigit(result(2).charAt(0)) ) 
        e2 = new Expression.Var("", result(2).toInt);
      else 
        e2 = variableStorage.findVariable(result(2));
      
      if(result(1).equals("+")) 
          println("Result: " + calculator.performBinaryOperation((x,y)=> x+y, -1, e1.value, e2.value));
      else if (result(1).equals("*"))
          println("Result: " + calculator.performBinaryOperation((x,y)=> x*y, -1, e1.value, e2.value));
      else if (result(1).equals("/"))
          println("Result: " + calculator.performBinaryOperation((x,y)=> x/y, -1, e1.value, e2.value));
      else if (result(1).equals("-"))
          println("Result: " + calculator.performBinaryOperation((x,y)=> x-y, -1, e1.value, e2.value));
      else if (result(1).equals("^"))
          println("Result: " + calculator.performBinaryOperation((x,y)=> x*y, 1, e1.value, e2.value));  
        
    case "end" => 
      System.exit(1234);
    
    case _ => 
      println("?? " + input);
      
  }
}