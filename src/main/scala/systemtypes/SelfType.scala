package systemtypes

object SelfType extends App {
  val aTest: TestExecutor = new TestExecutor with TestEnvironment {
    override val testName: String = "T-test"
  }
  aTest.execute()
}

trait TestEnvironment {
  val testName: String
}

class TestExecutor { test: TestEnvironment =>

  def execute(): Unit = {
    println(s"Executing ${test.testName}")
  }
}