package butch

import scala.+:
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object AllObjectTest extends App {
  val list: Seq[Int] = List(45, -45, 67, 0, 54, 43, -35, -45, 0, 0, 0, 1, 0, 1, -1, 2, -1, 0, 0, -1, 9, -1)

  /** *
   * Считаем колличество смен знаков у чисел в листе
   */
  def replaceSignsCount(list: Seq[Int]) = {
    val resCountWithZero = list.foldLeft(0)((m, n) => {
      if (m == 0) n.sign
      else if (m.sign != n.sign && (n != 0)) n.sign * (Math.abs(m) + 1)
      else m
    })
    Math.abs(resCountWithZero) - 1
  }

  /** *
   * Ищем последний в листе
   */
  @tailrec
  def lastElem(list: Seq[Int]): Int = list match {
    case h :: Nil => h
    case _ :: tail => lastElem(tail)
  }

  /** *
   * Ищем ппредпоследнийм в листе
   */

  @tailrec
  def preLastElem(list: Seq[Int]): Int = list match {
    case h :: _ :: Nil => h
    case _ :: tail => preLastElem(tail)
  }

  /** *
   * Ищем k-й елемент в листе
   */

  @tailrec
  def kElem(k: Int, list: Seq[Int]): Int = (k, list) match {
    case (0, h :: _) => h
    case (n, _ :: tail) => kElem(n, tail)
  }

  /** *
   * Ищем k-й елемент в листе
   */

  @tailrec
  def length(count: Int, list: Seq[Int]): Int = (count, list) match {
    case (0, Nil) => 0
    case (n, _ :: Nil) => n + 1
    case (n, _ :: tail) => length(n + 1, tail)
  }

  /** *
   * Разворачиваем k-й елемент в листе
   */
  def revertList(list: Seq[Int]): List[Int] = list match {
    case h :: tail => {
      revertList(tail) ::: List(h)
    }
    case Nil => Nil
  }

  /***
   * Замените серии одинаковых элементов списка на одиночный элемент
 */
  def replaceManyToOne(list: Seq[String]): List[String] =
    list.foldRight(List[String]()) { (right, acc) => {
      if (acc.isEmpty || acc.head != right) right :: acc else acc
  }
  }
  /***
     * Дан список. Замените серию одинаковых элементов на кортеж из элемента серии и длину серии,
   * но если серия состоит из одного элемента, то оставьте элемент без замены на кортеж.
   */
    def replaceManyToOneNumber(list: Seq[String]): Seq[Serializable] = {
      list.foldLeft(Map[String, Int]()) { (acc, left) => {
        acc.get(left).fold(acc + (left -> 1) ) { p: Int => {
          acc + (left -> (p + 1))
        }
        }
      }
    }.toSeq.map(value => if (value._2 != 1) value else value._1)
    }

  val listStr = List("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")

  println("list.length: " + replaceManyToOneNumber(listStr).mkString("; "))

}
