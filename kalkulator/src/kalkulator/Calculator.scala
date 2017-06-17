package kalkulator

class Calculator {
  
  def performBinaryOperation(g: (Int, Int) => Int, acc: Int, a: Int, b: Int): Int = {

    def tailRecursion(init: Int, res: Int): Int = {
      if (init >= b) {
        res
      } else {
        tailRecursion(init + 1, g(res, a));
      }
    }

    if (acc == -1) g(a, b)
    else tailRecursion(0, acc)
  }
  
  def performUnaryOperation(g:(Int) => Double, a: Int) = {
    g(a)
  }
  
}