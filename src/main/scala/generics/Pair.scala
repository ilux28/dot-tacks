package generics

class Pair[T](val first: T, val second: T) {

}

class Person(name: String, age: Int) {

}

class Student(name: String, age: Int, course: String) extends Person(name, age) {

}

object TestExample extends App {
//  def makeFriends[T](p: Pair[T]) =
}