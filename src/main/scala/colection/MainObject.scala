package colection

import scala.collection.mutable

object MainObject extends App {

  def groupByChar(str: String): String = {

    val charArray: Array[Char] = str.toCharArray

    val mpa: mutable.Map[String, String] = scala.collection.mutable.Map[String, String]()

    /** *
     * Берем мэп и в него складываем элементы массива:
     * если такой элемент есть, то презаписываем его, конкатенирую к его значения с его ключом,
     * если нет, то просто добавляем в мапу новый элемент
     */

    charArray.map(char => {
      val charString = char.toString
      mpa.get(charString) match {
        case Some(innerString) => mpa += (charString -> (innerString + charString))
        case None => mpa += (charString -> charString)
      }
    })

    /** *
     * затем берем только значения, сортируем относительно длинны, и склеиваем элементы
     */

    val result = mpa.map { case (_, v) => v }
      .toSeq
      .sortWith(_.length > _.length)
      .mkString("")
    result
  }
}
