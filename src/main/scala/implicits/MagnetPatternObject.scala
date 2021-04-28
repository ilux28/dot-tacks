package implicits

object MagnetPatternObject extends App {

  implicit class IncrementInt (x: Int) extends MathMagnet {
    override def apply(): Int = x + 1
  }

  implicit class IncrementStr (x: String) extends MathMagnet {
    override def apply(): Int = x.toInt+ 1
  }

  def increment(magnet: MathMagnet): Int = magnet()
//  val inc = increment _
  println(increment("5")) // 6
  println(increment(7)) // 8

  //Теперь сработает лифтинг
  val inc = increment _
  println(inc(9))
}
trait MathMagnet {
  def apply(): Int
}

