package multithreading

import java.util.concurrent.ForkJoinPool
import scala.concurrent.ExecutionContext

object ExecutorCreate extends App {
  val executor = new ForkJoinPool()
  executor.execute(() => println("This task is run asynchronously."))
//  Thread.sleep(500)

  val ectx = ExecutionContext.global
  ectx.execute(() => println("Running on the execution context."))

  val pool = new ForkJoinPool(2)
  val ectxG = ExecutionContext.fromExecutorService(pool)
  ectxG.execute(() => println("Running on the execution context again."))

  def execute(body : => Unit) = ExecutionContext.global.execute(
    () => {
      /** *
       * In this place we added code which the
       * execute this thread from ExecutionContext global
       * * */
      println(body)
    }
  )

  Thread.sleep(500)

}
