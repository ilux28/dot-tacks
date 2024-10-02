package multithreading

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}


class Creator[T] {


  val promise = Promise[T]()

  def show(): Unit = {


    if (promise.isCompleted) {

      promise.future.foreach(x => {
        println(x)
      })
    } else {

      println("no value")

    }


  }

  def create(v: T): Unit = {


    promise.future.onComplete {
      case Success(z) => println(z)
      case Failure(ex) => println("no value")
    }


    if (promise.isCompleted) {
      println("value already exists")

    } else {

      println("value created")
      promise.complete(Try(v))
//      promise.trySuccess(v)

    }

  }

}
