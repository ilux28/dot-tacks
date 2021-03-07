package mail

object MailValidator {

  val emailPattern = """^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$""".r

  def isValidEmail(str: String): Boolean = {
    str match {
      case emailPattern(_*) => true
      case _ => false
    }}

  def main(args: Array[String]): Unit = {
    print("Please enter your email:" )
    val stringIn = scala.io.StdIn.readLine()
    val result = isValidEmail(stringIn)
    println(result)

  }
}
