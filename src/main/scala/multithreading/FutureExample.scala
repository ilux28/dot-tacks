package multithreading


import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Random, Success, Try}

object FutureExample extends App {
  val rdm = new Random()
  def retry[A](action: () =>  Future[A], condition: A => Boolean): Future[A] = {
    action().filter(condition).recoverWith {
      case _: Throwable => retry(action, condition)
    }
  }
  val random = new Random()
  val aString = List("a", "ab", "abc", "abcd")

  val action = () => Future{
    val idx = random.nextInt(4)
    aString(idx)
  }

  retry(action, (s: String) =>  s.length > 3).foreach(result => println(result))

  /***
   * Выводим первый готовый футур
   * @param futureA
   * @param futureB
   * @tparam A
   * @return
   */
  def firstToComplete[A](futureA: Future[A], futureB: Future[A]): Future[A] = {
    val promise = Promise[A]
    futureB.onComplete(promise.tryComplete)
    futureA.onComplete(promise.tryComplete)
    Thread.sleep(600)
    promise.future
  }

  val futureA = Future{
   Thread.sleep(200)
    12
  }

  val futureB = Future{
    Thread.sleep(100)
    37
  }

//  println(firstToComplete(futureA, futureB))
  /**
   * Пропускаем первый фьючер и выполняем второй
   * @param futureA
   * @param futureB
   * @tparam A
   * @tparam B
   */

  def inSequence[A,B](futureA: Future[A], futureB: Future[B]): Unit = futureA
    .flatMap(_ => futureB).foreach(f => println(f))

//  inSequence(Future(12), Future(34))

//  def simpleFuture(value: Int): Future[Int] = Future(value)
//
//  simpleFuture(12).onComplete{
//    case Success(res) => println(s"$res")
//  }
//  println(simpleFuture(12))
//  println(simpleFuture(12).value)
//  val sourceCity = FlightNetwork.fetchSource("r1-msc")

//  val source: Future[FlightProfile] = FlightNetwork.fetchSource("r1-msc")
//  Thread.sleep(rdm.nextInt(5000))
//  println(source)
//  val sourceCity: Future[String] = source.map(profile => profile.city) // получили тип Future[String]
//
//  val destination: Future[FlightProfile] = source.flatMap(profile => FlightNetwork.fetchDestination(profile))
//
//  val destinationFiltered: Future[FlightProfile] = destination.filter(profile => profile.city.startsWith("S"))

//  for {
//    source <- FlightNetwork.fetchSource("r1-msc")
//    destination <- FlightNetwork.fetchDestination(source)
//  } source.search(destination)

//  destination.flatMap(a => source.map(b => b.search(a)))

//  sourceCity.onComplete {
//    case Success(sourceProfile) => {
//      val destination = FlightNetwork.fetchDestination(sourceProfile)
//
//      destination.onComplete {
//        case Success(destinationProfile) => sourceProfile.search(destinationProfile)
//        case Failure(e) => e.printStackTrace()
//      }
//
//    }
//    case Failure(ex) => ex.printStackTrace()
//  }

//  Thread.sleep(rdm.nextInt(5000)) // даем Future время полностью отработать

  case class FlightProfile(id: String, city: String) {
    def search(destination: FlightProfile) = {
      println(s"found flights from ${this.city} to ${destination.city}")
    }
  }

  object FlightNetwork {

    val sources = Map(
      "r1-msc" -> "Moscow",
      "r2-spb" -> "St Petersburg"
    )
    val routes = Map(
      "r1-msc" -> "r2-spb"
    )

    def fetchSource(id: String): Future[FlightProfile] = Future {
      Thread.sleep(rdm.nextInt(100)) // имитируем вычисления
      FlightProfile(id, sources(id))
    }

    def fetchDestination(profile: FlightProfile): Future[FlightProfile] = Future {
      Thread.sleep(rdm.nextInt(200))
      val destinationId = routes(profile.id)
      FlightProfile(destinationId, sources(destinationId))
    }
  }
}

