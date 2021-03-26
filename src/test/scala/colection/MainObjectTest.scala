package colection

import org.scalatest.FunSuite

class MainObjectTest extends FunSuite {
  test("MainObject.groupByChar test"){
    val str = "asdasdaaaweqbbbbasdasd"
    assert(MainObject.groupByChar(str) == "aaaaaaabbbbssssddddqew")
  }
  test("MainObject.groupByCharElems test"){
    val charArray = "asdasdaaaweqbbbbasdasd".toCharArray
    assert(MainObject.groupByCharElems(charArray) == "aaaaaaabbbbssssddddqew")
  }

}