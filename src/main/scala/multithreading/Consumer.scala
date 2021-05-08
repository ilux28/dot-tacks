package multithreading

import scala.collection.mutable
import scala.util.Random

class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread {
  override def run(): Unit = {
    val random = new Random()
    while (true) {
      buffer.synchronized{
        while (buffer.isEmpty) {
          println(s"Потрребитель $id: ожидаю значения")
          buffer.wait()
        }
        val elem = buffer.dequeue()
        println(s"Потрребитель $id: получил значение $elem")
        buffer.notifyAll()
      }
      Thread.sleep(random.nextInt(500))
    }
  }
}
