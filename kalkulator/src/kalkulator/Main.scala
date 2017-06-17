package kalkulator

object Main extends App {
  
  var variableStorage = new VariableStorage();
  
  val calc = new Calculator();
  val inputStreamEvaluator = new InputStreamEvaluator(variableStorage,calc);
  
  println("Welcome to Calculator!");
  
  while(true) {
    var input:String = scala.io.StdIn.readLine();
    
    inputStreamEvaluator.evaluate(input);
  }
}