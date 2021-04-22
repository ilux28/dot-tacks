package butch

import scala.annotation.tailrec

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
    case h :: tail => revertList(tail) ::: List(h)
    case Nil => Nil
  }

  /** *
   * Замените серии одинаковых элементов списка на одиночный элемент
   */
  def replaceManyToOne(list: Seq[String]): List[String] =
    list.foldRight(List[String]()) { (right, acc) => {
      if (acc.isEmpty || acc.head != right) right :: acc else acc
    }
    }

  /** *
   * Дан список. Замените серию одинаковых элементов на кортеж из элемента серии и длину серии,
   * но если серия состоит из одного элемента, то оставьте элемент без замены на кортеж.
   */
  def replaceManyToOneNumber(list: Seq[String]): Seq[Serializable] = {
    list.foldLeft(Map[String, Int]()) { (acc, left) => {
      acc.get(left).fold(acc + (left -> 1)) { p: Int => {
        acc + (left -> (p + 1))
      }
      }
    }
    }.toSeq.map(value => if (value._2 != 1) value else value._1)
  }

  /** *
   * Дан список. Замените серию одинаковых элементов на кортеж из элемента серии и длину серии
   */
  def replaceTupleOnTheListChar(list: List[Char]): List[(Int, Char)] = {
    if (list.isEmpty) Nil
    else {
      val (matchList, otherList) = list.span(_ == list.head)
      (matchList.length, matchList.head) :: replaceTupleOnTheListChar(otherList)
    }
  }

  /** *
   * Дан список chars. true - если количество "(" равно ")", false если ":-)"; "())(";
   */
  def balance1(chars: List[Char]): Boolean = {
    val resInt = chars.foldLeft(0)((acc, char) => {
      if (acc < 0) {
        return false
      } else if (char == '(') acc + 1 else if (char == ')') acc - 1 else acc
    })
    resInt % 2 == 0
  }

  def balance2(chars: List[Char]): Boolean = {
    def count(i: Int, chars: List[Char]): Int = {
      if (i < 0) i else {
        chars match {
          case head :: tail => {
            val acc = if (head == '(') i + 1 else if (head == ')') i - 1 else i
            count(acc, tail)
          }
          case _ => i
        }
      }
    }

    count(0, chars) % 2 == 0
  }

  /***
   * Считаем количество одинаковых символов в строке
   * @param someString
   * @return
   */
  def countChars(someString: String): List[(Char, Int)] = {
    val charList = someString.toCharArray.toList
    def putToMap(elem: Char, map: Map[Char, Int]): Map[Char, Int] = map.get(elem).fold{
      map + (elem -> 1)
    }
    {
      value => {
        map + (elem -> (value + 1))
      }
    }

    @tailrec
    def countCharsRec(charArray: List[Char], map: Map[Char, Int]): Map[Char, Int] = {
      charArray match {
        case head :: tail => {
          val mapResult: Map[Char, Int] = putToMap(head.toLower, map)
          countCharsRec(tail, mapResult)
        }
        case head :: Nil => putToMap(head, map)
        case Nil => map
      }
    }

    countCharsRec(charList, Map[Char, Int]()).toList.sortBy(_._2)
  }

  /***
   * Разворачиваем самописный лист
   * @param head
   * @param tail
   * @tparam A
   */

  case class MyList[A](head: A, tail: Option[MyList[A]]) {
    // [1, 2, 3] => [3, 2, 1], O(n)
    def reverse: MyList[A] =
      tail match {
        case Some(tail) => reverseA(tail, MyList(head, None))
        case None => this
      }
  }

  def reverseA[A](list: MyList[A], listA: MyList[A]): MyList[A] = {
    list match {
      case MyList(head, None) => MyList(head, Some(listA))
      case MyList(head, Some(tail)) => {
        reverseA(tail, MyList(head, Some(listA)))
      }
    }
  }

  val str = "Sst"

  val listStr = List("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")

  println("countChars " + countChars(str).mkString(", "))

}
