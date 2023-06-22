/**
 * Есть список из интов. Вывести значения локальных максимумов
 * Для List(1, 2, 5, 4, 6, 2) ответ List(5, 6)
 * Для List(1, 2, 2, 4) ответ List() - максимумов нет
 * Для List(1, 3, 3, 1) ответ List(3) - плато из троек также является локальным максимумом
 */

val list1 = List(1, 2, 5, 4, 6, 2)
val list2 = List(1, 2, 2, 4)
val list3 = List(1, 3, 3, 1)

def locMax (x: List[Int]) = x.foldLeft((0, List[Int]()))((tuple, b) => {
  tuple match {
    case (0, Nil) => (b , Nil)
    case (h, g) if h > b => (b, h :: g)
    case (h, g) if h < b => (b, g)
    case (h, g) if h == b => (h, g)

  }

})

locMax(list1)
locMax(list2)
locMax(list3)

/**
 * LoadWithCheck
 * дана функция загрузки из базы (offset: Int, limit: Int) => Seq[T]
 * дана функция проверки загруженных значений check: T => Boolean
 * Написать функцию, которая будет загружать из базы n значений,
 * удоворяющих check
 * (если в базе значений меньше, то загрузить все, что есть)
 **/
def check(x: Int): Boolean = x % 2 == 0

def load(offset: Int, limit: Int): Seq[Int] = (1 to 10).drop(offset).take(limit)

def loadWithCheck(n: Int): Seq[Int] = {

  def recLoadWithCheck(start: Int, size: Int, result: Seq[Int]): Seq[Int] = {

    val result1 = result ++ load(start, size).filter(check)


    if (result1.size < n && result1.nonEmpty && result.size != result1.size) {

      recLoadWithCheck(result1.size - 1 ,n - result1.size, result1)

    } else result1

  }


  recLoadWithCheck(0, n, Seq[Int]())

}

loadWithCheck(4)