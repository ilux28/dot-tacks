package multithreading

import java.util.concurrent.Executors

object ThreadExample extends App {
  def runThreads(threadNum: Int, i: Int = 1): Thread = new Thread(() => {
    if (i < threadNum) {
      val thread = runThreads(threadNum, i + 1)
      thread.start()
      thread.join()
    }
    print(s"thread_$i ")
  })

  runThreads(3).start()
//  val pool = Executors.newFixedThreadPool(3)
//
//  val firstThread = new Thread(() => (1 to 3).foreach(_ => println("1st thread: I run in parallel!")))
//  val secondThread = new Thread(() => (1 to 3).foreach(_ => println("2st thread: I also run in parallel!")))
//
//  val runnable  = new Runnable {
//    override def run(): Unit = println("I run in parallel!")
//  }
//
//  pool.execute(runnable)
//
//  if (!pool.isShutdown) pool.shutdown()
//  firstThread.start()
//  firstThread.join()
//
//  secondThread.start()
}
class BankAccount(var amount: Int) {
  override def toString: String = s"На счету $amount"
}