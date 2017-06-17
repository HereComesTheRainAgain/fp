package kalkulator

class InputStreamEvaluator(var calculatorStorage:CalculatorStorage, var calculator:Calculator) {
  
  def evaluate (input: String) = input match {
    
    case operation if operation.contains(" + ") || operation.contains(" / ") 
                      || operation.contains(" - ") || operation.contains(" * ") || operation.contains(" ^ ") =>
      var result = operation.split(" ");
      
      var e1:Value.Val = new Value.Var("_first", -1);
      var e2:Value.Val = new Value.Var("_second", -1);
      
      try {
        
        if (Character.isDigit(result(0).charAt(0)) ) 
          e1 = new Value.Var("", result(0).toInt);
        else 
          e1 = calculatorStorage.getValueFromStorage(result(0)).get;
        
        if (Character.isDigit(result(2).charAt(0)) ) 
          e2 = new Value.Var("", result(2).toInt);
        else 
          e2 = calculatorStorage.getValueFromStorage(result(2)).get;
      
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
    
    case log if log.startsWith("log") =>
      var result = log.split(" ");
      
      var e1:Value.Val = new Value.Var("_first", -1);
      
      try {
      
        if (Character.isDigit(result(1).charAt(0)) ) 
            e1 = new Value.Var("", result(1).toInt);
          else 
            e1 = calculatorStorage.getValueFromStorage(result(1)).get;
        
        println("Result: " + calculator.performUnaryOperation((x) => math.log(x), e1.value));
      
      } catch {
        case e:NoSuchElementException => 
             println("variable not stored: " + result(0));
      }
      
    case store if store.startsWith("store") => 
      var result = store.split(" ");
      
      if(result(1).equals(result(1).capitalize))
        calculatorStorage.addConstant(result(1), result(2).toInt);
      else
        calculatorStorage.addVariable(result(1), result(2).toInt);
      
      calculatorStorage.printStoredValues();
      
    case "end" => 
      System.exit(1234);
    
    case _ => 
      println("?? " + input);
      
  }
}