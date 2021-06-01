package pubstub

import akka.actor.ActorSystem
import akka.testkit.EventFilter
import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class PubSubMediatorSpec extends WordSpec with Matchers with BeforeAndAfterAll {
  implicit val system = ActorSystem("pub-sub-mediator-spec-system")

  "A PubSubMediator" should {
    "be suited for getting started" in {
      EventFilter.debug(occurrences = 1, pattern = s"started.*${classOf[PubSubMediator].getName}").intercept {
        system.actorOf(PubSubMediator.props)
      }
    }
  }

  override protected def afterAll() = {
    Await.ready(system.terminate(), Duration.Inf)
    super.afterAll()
  }


}
