package implicits

object TypeClass extends  App {
  val greeting = "My name is [ X ] and I'm a [ Y ]. And I have [ Z ] years of experience."
  import Greeting._
  implicit class GreetingStr(val greeting: String) extends AnyVal {
    def isMiddle: Boolean = greeting == "middle"
  }

  println(greeting.level.isMiddle)
}

case class Greeting(text: String) {
  def name: String = text
  def occupation: String = text
  def level: String = text
}

object Greeting {
  implicit def str2Greeting(param: String): Greeting = Greeting(param)
}