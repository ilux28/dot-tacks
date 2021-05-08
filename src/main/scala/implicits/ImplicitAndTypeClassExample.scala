package implicits

object ImplicitAndTypeClassExample extends App {
  implicit object DoubleConverter extends Converter[Double] {
    override def convert(value: Double): String = s"converted value: $value"
  }
  println(Converter[Double].convert(2.0))
}

trait Converter[T] {
  def convert(value: T): String
}

object Converter {
  def convert[T](value: T)(implicit converter: Converter[T]): String = converter.convert(value)
  def apply[T](implicit converter: Converter[T]): Converter[T] = converter
}