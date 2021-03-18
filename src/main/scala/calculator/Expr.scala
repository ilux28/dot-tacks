package calculator

abstract class Expr
case class Number(value : Double) extends Expr
case class UnaryOp(operator : String, arg : Expr) extends Expr
case class BinaryOp(operator : String, left : Expr, right : Expr) extends Expr

object Calc {
  def evaluate(e: Expr): Double = {
    simplify(e) match {
      case Number(x) => x
      case UnaryOp("minus", x) => -(evaluate(x))
      case BinaryOp("summa", x1, x2) => (evaluate(x1) + evaluate(x2))
      case BinaryOp("minus", x1, x2) => (evaluate(x1) - evaluate(x2))
      case BinaryOp("multiply", x1, x2) => (evaluate(x1) * evaluate(x2))
      case BinaryOp("division", x1, x2) => (evaluate(x1) / evaluate(x2))
    }
  }

  def simplify(e: Expr): Expr = {
    // сначала упрощаем подвыражения
    val simpSubs = e match {
      // упрощаем обе части выражения
      case BinaryOp(op, left, right) => BinaryOp(op, simplify(left), simplify(right))
      // упрощаем операнд
      case UnaryOp(op, operand) => UnaryOp(op, simplify(operand))
      // здесь нечего упрощать
      case _ => e
    }

    def simplifyTop(expr: Expr): Expr = {
      expr match {
        case UnaryOp("minus", UnaryOp("minus", x)) => x
        case UnaryOp("summa", x) => x
        case BinaryOp("multiply", x, Number(1)) => x
        case BinaryOp("multiply", Number(1), x) => x
        case BinaryOp("multiply", x, Number(0)) => Number(0)
        case BinaryOp("multiply", Number(0), x) => Number(0)
        case BinaryOp("division", x, Number(1)) => x
        case BinaryOp("summa", x, Number(0)) => x
        case BinaryOp("summa", Number(0), x) => x
        case _ => expr
      }

    }

    simplifyTop(simpSubs)
  }

  def main(args: Array[String]): Unit = {
    val expression1 = BinaryOp("summa", Number(2), Number(2))
    val result = evaluate(expression1)
    println(result)
  }

}