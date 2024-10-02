package multithreading

import scala.concurrent.Future

object FuturePromiseExample extends App {

  def execute(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start()
    t
  }



  val v = new Creator[String]

  (1 to 10).foreach(_ => execute {
    v.show()
    v.create(s"SomeString")
    v.show()
  })

}
