package kalkulator

object Main extends App {
  
  val inputStreamEvaluator = new InputStreamEvaluator(new VariableStorage(), new Calculator());
  
  println("Welcome to Calculator!");
  
  while(true) {
    inputStreamEvaluator.evaluate(scala.io.StdIn.readLine());
  }
}