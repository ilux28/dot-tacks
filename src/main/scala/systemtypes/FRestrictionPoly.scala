package systemtypes

object FRestrictionPoly extends App {
  implicit class PurchaseOps[A](fruit: A) {
    def buy(implicit fruitTypeClassInstance: Fruit[A]): List[A] =
      fruitTypeClassInstance.buy(fruit)
  }

  val apple = new Apple
  apple.buy
}

/***
 * Задаем рекурсивный тип вместе с self типом
 * чтобы ограничить и не смешивать типы в наследниках
 * @tparam T
 */

//trait Fruit[T <: Fruit[T]] { self: T =>
//  def buy: List[Fruit[T]]
//}
//
//class Apple extends Fruit[Apple] {
//  override def buy: List[Apple] = ???
//}
//class Banana extends Fruit[Banana] {
//  override def buy: List[Banana] = ???
//}

//trait Fruit
//
//trait Purchase[A] {
//  def buy(fruit: A): List[A]
//}
//
//class Apple extends Fruit
//
//object Apple {
//  implicit object ApplePurchase extends Purchase[Apple] {
//    override def buy(fruit: Apple): List[Apple] = ???
//  }
//
//  implicit class PurchaseOps[A](fruit: A) {
//    def buy(implicit fruitPurchase: Purchase[A]): List[A] = {
//      fruitPurchase.buy(fruit)
//    }
//  }
//  val apple = new Apple
//  apple.buy
//  /*
//    теперь срабатывает
//    new PurchaseOps[Apple](apple).buy(Apple.ApplePurchase)
//
//    Apple.ApplePurchase - исплиситна переменная, требующаяся
//    методу buy
//
//   */
//}

/***
 * Усовершенствованный вариант предыдущего
 * @tparam A
 */
trait Fruit[A] {
  def buy(a: A): List[A]
}

class Apple
object Apple {
  implicit object ApplePurchase extends Fruit[Apple] {
    override def buy(a: Apple): List[Apple] = {
      println("bought an apple")
      List()
    }
  }
}
