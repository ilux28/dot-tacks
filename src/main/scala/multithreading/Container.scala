package multithreading

import scala.collection.mutable
import scala.util.Random

class Container {

  def work(nConsumers: Int, nProducers: Int): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 20

    (1 to nConsumers).foreach(i => new Consumer(i, buffer).start())
    (1 to nProducers).foreach(i => new Producer(i, buffer, capacity).start())
  }

}

object Container extends App {

  val container = new Container

  container.work(3, 4)
}