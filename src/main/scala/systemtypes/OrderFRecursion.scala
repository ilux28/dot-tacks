package systemtypes

object OrderFRecursion extends  App {
  class VegetarianMenu
  object VegetarianMenu {
    implicit object VegetarianOrder extends Menu[VegetarianMenu] {
      override def order(a: VegetarianMenu): List[VegetarianMenu] = {
        println(s"Vegetarian order")
        List()
      }
    }
  }

  class StandardMenu
  object StandardMenu {
    implicit object StandardOrder extends Menu[StandardMenu] {
      override def order(a: StandardMenu): List[StandardMenu] = {
        println("standard order")
        List()
      }
    }
  }

  implicit class Order[A](menu: A) {
    def order(implicit menuTypeClassInstance: Menu[A]): List[A] = {
      menuTypeClassInstance.order(menu)
    }
  }

  val standardMenu = new StandardMenu
  val vegetarianMenu = new VegetarianMenu
  vegetarianMenu.order
  standardMenu.order
}

trait Menu[A] {
  def order(a: A): List[A]
}