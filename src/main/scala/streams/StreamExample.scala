package streams

import akka.stream._
import akka.stream.scaladsl._
import akka.{ Done, NotUsed }
import akka.actor.ActorSystem
import akka.util.ByteString
import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths

object StreamExample extends App {

  implicit val system: ActorSystem = ActorSystem("StreamStart")

  val source: Source[Int, NotUsed] = Source(1 to 100)

//  source.runForeach(i => println(s"This is $i element of stream!"))

//  val done: Future[Done] = source.runForeach(i => println(s"This is $i element of stream!"))

  val factorial = source.scan(BigInt(1))((acc, i) => acc * i)

  val resultFactorial = factorial.map(num => ByteString(s"$num/n")).runWith(FileIO.toPath(Paths.get("factorials.txt")))

//  implicit val ec: ExecutionContextExecutor = system.dispatcher

//  done.onComplete(_ => system.terminate())
}

