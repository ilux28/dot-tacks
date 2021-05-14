package systemtypes

object CakePatternExample extends App {

}

trait User {
  def info(user: String) = user
}

trait AppComponent { self: User =>
  def  detailInfo(user: String) = s"Detailed info ${info(user)}"
}

trait Application {self: AppComponent => }

trait RegisteredUser extends User
trait AnonymousUser extends User

trait Analytics extends AppComponent with AnonymousUser
trait Profile extends AppComponent with RegisteredUser
