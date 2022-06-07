import scala.io.StdIn

package object console {
  /** * Объявить модель */
  val greet = {
    println("Как тебя зовут?")
    val name = StdIn.readLine()
    println(s"Привет, $name")
  }

  sealed trait Console[A] {
    self =>
    def map[B](f: A => B): Console[B] = FlatMap[A, B](self, a => Console.succeed(f(a)))

    def flatMap[B](f: A => Console[B]): Console[B] = FlatMap[A, B](self, f)
  }

  case class PrintLine[A](str: String, rest: Console[A]) extends Console[A]

  case class ReadLine[A](f: String => Console[A]) extends Console[A]

  case class Return[A](v: () => A) extends Console[A]

  case class FlatMap[A, B](c: Console[A], f: A => Console[B]) extends Console[B]

  object Console {
    def succeed[A](v: => A): Console[A] = Return(() => v)

    def printLine(string: String): Console[Unit] = PrintLine(string, Return(() => ()))

    def readLine: Console[String] = ReadLine(str => succeed(str))
  }

  val r: Console[Unit] = PrintLine("Как тебя зовут?", ReadLine(name => PrintLine(s"Привет, $name", Return(() => ()))))

  val greet2 = {
    println("Как тебя зовут?")
    val name = StdIn.readLine()
    println(s"Привет, $name")
  }

  val r2: Console[Unit] = for {
    _ <- Console.printLine("Как тебя зовут?")
    name <- Console.readLine
    _ <- Console.printLine(s"Привет, $name")
  } yield ()

  def interpret[A, B](console: Console[A]): A = console match {
    case PrintLine(str, rest) =>
      println(str)
      interpret(rest)
    case ReadLine(f) =>
      interpret(f(StdIn.readLine()))
    case Return(v) =>
      v()
    case v: FlatMap[A, B] =>
      interpret(v
        .f(
        interpret(v.c)
      ))
  }
}
