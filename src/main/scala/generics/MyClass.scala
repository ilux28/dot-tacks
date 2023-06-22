package generics

class MyClass[+T] {
  def this(arg: T) {
    this()
  }
}

object Demo extends App {

  val x: MyClass[Object] = new MyClass[String]
//  val y: MyClass[String] = new MyClass[Object]
  val z: MyClass[String] = new MyClass[String]


  val child :(Int => Int) => (Int => Int) = ???
  val parent :(Int => Int) => (Int => Int) = child

}
