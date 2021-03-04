import scala.collection.mutable

object MainObject {

  def groupByChar(str: String): String = {

    val charArray: Array[Char] = str.toCharArray

    val mpa: mutable.Map[String, String] = scala.collection.mutable.Map[String, String]()

    charArray.map(char => {
      val charString = char.toString
      mpa.get(charString) match {
        case Some(innerString) => mpa += (charString -> (innerString + charString))
        case None => mpa += (charString -> charString)
      }
    })

    val result: String = mpa.map { case (_, v) => v }
      .toSeq
      .sortWith(_.length > _.length)
      .mkString("")
    result
  }

  def main(args: Array[String]): Unit = {
    val str = "asdasdaaaweqbbbbasdasd"
    println(groupByChar(str))

  }
}
