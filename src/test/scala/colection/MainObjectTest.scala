package colection

import org.scalatest.FunSuite

class MainObjectTest extends FunSuite {
  test("MainObject.groupByChar test"){
    val str = "asdasdaaaweqbbbbasdasd"
    assert(MainObject.groupByChar(str) == "aaaaaaabbbbssssddddqew")
  }
}