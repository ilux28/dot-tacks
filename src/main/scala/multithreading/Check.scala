package multithreading

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Check[T] {

  def check(subscription: () => Future[T], isSubscribed: T => Boolean): Future[T] = {
      subscription.apply()
//    Future("")
  }

}
