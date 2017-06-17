package kalkulator

class InputStreamEvaluator(var calculatorStorage:CalculatorStorage, var calculator:Calculator) {
  
  def evaluate (input: String) = input match {
    
    case store if store.startsWith("store") => 
      var result = store.split(" ");
      calculatorStorage.addVariable(result(1), result(2).toInt);
      calculatorStorage.printVarStorage();
    
    case operation if operation.contains(" + ") || operation.contains(" / ") 
                      || operation.contains(" - ") || operation.contains(" * ") || operation.contains(" ^ ") =>
      var result = operation.split(" ");
      
      var e1 = new Expression.Var("_first", -1);
      var e2 = new Expression.Var("_second", -1);
      
      try {
        
        if (Character.isDigit(result(0).charAt(0)) ) 
          e1 = new Expression.Var("", result(0).toInt);
        else 
          e1 = calculatorStorage.findVariable(result(0)).get;
        
        if (Character.isDigit(result(2).charAt(0)) ) 
          e2 = new Expression.Var("", result(2).toInt);
        else 
          e2 = calculatorStorage.findVariable(result(2)).get;
      
        if (result(1).equals("+")) 
            println("Result: " + calculator.performBinaryOperation((x,y)=> x+y, -1, e1.value, e2.value));
        else if (result(1).equals("*"))
            println("Result: " + calculator.performBinaryOperation((x,y)=> x*y, -1, e1.value, e2.value));
        else if (result(1).equals("/"))
            println("Result: " + calculator.performBinaryOperation((x,y)=> x/y, -1, e1.value, e2.value));
        else if (result(1).equals("-"))
            println("Result: " + calculator.performBinaryOperation((x,y)=> x-y, -1, e1.value, e2.value));
        else if (result(1).equals("^"))
            println("Result: " + calculator.performBinaryOperation((x,y)=> x*y, 1, e1.value, e2.value)); 
        
      } catch {
        case e:NoSuchElementException => 
            if (e1.name.equals("_first"))
                println("variable not stored: " + result(0));
            else if (e2.name.equals("_second"))
                println("variable not stored: " + result(2));
      }
      
    case "end" => 
      System.exit(1234);
    
    case _ => 
      println("?? " + input);
      
  }
}