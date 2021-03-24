package butch

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class NewBatchClass {
    private def func(i: Int): Future[Int] = Future {
      println(s"running func($i)")
      Thread.sleep(1500)
      i * 100
    }

    def batchTraverse(in: Seq[Int], size: Int)(f: Int => Future[Int]): Future[Seq[Int]] = {
      val resCount = in.grouped(size).toList

      def recLaunch(partIn: Seq[Int], acc: Seq[Int], count: Int): Future[Seq[Int]] =  {
        val resFuture: Future[Seq[Int]] = Future.traverse(partIn)(f)
        resFuture.flatMap((inner: Seq[Int]) => {
          if (count < resCount.length) {
            recLaunch(resCount(count), (acc ++ inner), (count + 1))
          } else {
            Future(acc ++ inner)
          }
        })
      }
      recLaunch(resCount.head, Nil, 1)
    }

    val seq = Seq(12, 234, 67, 8457, 577, 56, 78)
    val result = Await.result(batchTraverse(seq, 2)(func), Duration.Inf )
    println(result.mkString("; "))
}
