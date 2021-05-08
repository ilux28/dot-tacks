package multithreading

import scala.collection.mutable
import scala.util.Random

class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int) extends Thread {
  override def run(): Unit = {
    val random = new Random()
    var i = 0

    while(true) {
      buffer.synchronized {
        while (buffer.size == capacity) {
          println(s"производитель $id: буфет заполнен - ожидаю...")
          buffer.wait()
        }

        println(s"производитель $id: предосталяю значение " + i)
        buffer.enqueue(i)

        buffer.notifyAll()

        i += 1
      }

      Thread.sleep(random.nextInt(500))
    }
  }
}
