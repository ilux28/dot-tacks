package implicits

object MainImplicit extends App {

  trait Converter[T] {
    def convert(value: T): String
  }

  object Converter {
    def convert[T](value: T)(implicit converter: Converter[T]): String =
      converter.convert(value)
  }

  implicit object DoubleConverter extends Converter[Double] {
    override def convert(value: Double): String = s"converted value: $value"
  }

  case class UserId(id: Int)

  trait Printer[T] {
    def print(value: T): String
  }

  object Printer {
    def print[T](value: T)(implicit printer: Printer[T]): String = printer.print(value)

    def apply[T](implicit printer: Printer[T]): Printer[T] = printer //без него работать не будет, так как
  }

  implicit object IdPrinter extends Printer[UserId] {
    override def print(value: UserId): String = s"print: ${value.id}"
  }

  val price = 500
  val discount = 50

  implicit class PriceOps(p: Price)  {
    def -(n: Int): Int = p.price - n
//    def -(p: Price)
  }

  println(Price(price) - discount)
  Printer[UserId].print(UserId(12))
//  Printer[UserId].print(UserId(12))
}

case class Person(age: Int) {
  def increaseAge: Unit = println(age + 1)
}

object Person {
  implicit def str2Person(param: String): Person = Person(param.toInt)
}

case class Price(price: Int)