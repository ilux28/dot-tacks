package butch

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object AllObjectTest extends App {
  /***
   * Считаем колличество смен знаков у чисел
   */
  val list = List(45, -45, 67, 0, 54, 43, -35, -45, 0, 0 , 0, 1, 0, 1, -1, 2, -1, 0 , 0, -1, 9, -1)

  val resCountWithZero = list.foldLeft(0)((m, n) => {
    if (m == 0) {
      n.sign
    } else if (m.sign != n.sign && (n != 0)) {
      n.sign * (Math.abs(m) + 1)
    }  else {
      m
    }
  })
  val resCount = Math.abs(resCountWithZero) - 1
  println(resCount)
}
