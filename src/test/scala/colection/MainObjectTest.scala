package colection

import org.scalatest.FunSuite

class MainObjectTest extends FunSuite {
  test(""){
    val str = "asdasdaaaweqbbbbasdasd"
    assert(MainObject.groupByChar(str) == "aaaaaaabbbbssssddddqew")
  }
}