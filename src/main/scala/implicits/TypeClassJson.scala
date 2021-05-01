package implicits

import implicits.TypeClassJson.JsonObjectConverter

object TypeClassJson extends App {

  implicit object JsonStringConverter extends ConverterType[JsonString] {
    override def convert(value: JsonString): String = s""""${value.str}""""
  }

  implicit object JsonIntegerConverter extends ConverterType[JsonInteger] {
    override def convert(value: JsonInteger): String = s"""${value.number}"""
  }

  private def primitiveConvert() = {

  }

  implicit object JsonObjectConverter extends ConverterType[JsonObject] {
    override def convert(obj: JsonObject): String = {
      val jsonResult = obj.map.map {case (key, value) => {
        val convertValue: String = value match {
          case arrayObj: JsonArray => s"""[${arrayObj.list.map({
            case anyObj: JsonObject => convert(anyObj)
          }).mkString(",")}]"""
          case strObj: JsonString => ConverterToJson[JsonString].convert(strObj)
          case intObj: JsonInteger => ConverterToJson[JsonInteger].convert(intObj)
          case jsonObj: JsonObject => convert(jsonObj)
        }
        s""""$key":$convertValue"""
      }
      }.mkString(",")
      s"{$jsonResult}"
    }
  }

  implicit object CustomerToJsonObjectConverter extends ConvertToObject[Customer] {
    override def convertToJsonObject(value: Customer): JsonObject = JsonObject(Map(
      "email" -> JsonString(value.email),
      "name" -> JsonString(value.name)
    ))
  }

  implicit object ItemToJsonObjectConverter extends ConvertToObject[Item] {
    override def convertToJsonObject(value: Item): JsonObject = JsonObject(Map(
      "id" -> JsonInteger(value.id),
      "description" -> JsonString(value.description)
    ))
  }

  implicit object OrderToJsonObjectConverter extends ConvertToObject[Order] {
    override def convertToJsonObject(value: Order): JsonObject = JsonObject(Map(
      "customer" -> ConverterToJsonObject[Customer].convertToJsonObject(value.customer),
      "items" -> JsonArray(value.items.map(elem => ConverterToJsonObject[Item].convertToJsonObject(elem)))))
  }

  val data = JsonObject(Map(
    "name" -> JsonString("Bob"),
    "items" -> JsonArray(
      List(
        JsonInteger(1),
        JsonString("Stuff to buy"))
    )
  ))

  val orderJson = """{"customer":{"email":"alice@mail.com","name":"Alice"},"items":[{"id":1,"description":"first item"},{"id":2,"description":"second item"}]}"""

  val customer = Customer("alice@mail.com", "Alice")
  val order: Order = Order(customer, List(
    Item(1, "first item"),
    Item(2, "second item"),
  ))

  implicit class ToJson(order: Order) {
    def toJson: JsonObject = ConverterToJsonObject[Order].convertToJsonObject(order)
  }

  implicit class Stringify(jsonObject: JsonObject) {
    def stringify: String = ConverterToJson[JsonObject].convert(jsonObject)
  }

  val example = """{"name":"Bob","items":[1,"Stuff to buy"]}"""
  val example1 = JsonObject(Map("customer" -> JsonObject(Map("email" -> JsonString("alice@mail.com"), "name" -> JsonString("Alice"))), "items" -> JsonArray(List(JsonObject(Map("id" -> JsonInteger(1), "description" -> JsonString("first item"))), JsonObject(Map("id" -> JsonInteger(2), "description" -> JsonString("second item")))))))
  println(order.toJson)
  println(orderJson)
//  println(customer.customerToJson)
//  println(example1)
  println(order.toJson.stringify)

}

case class Customer(email: String, name: String)
case class Item(id: Int, description: String)
case class Order(customer: Customer, items: List[Item])

case class  JsonString(str: String)
case class  JsonInteger(number: Int)
case class  JsonArray(list: List[AnyRef])
case class  JsonObject(map: Map[String, AnyRef])

trait ConverterType[T] {
  def convert(value: T): String
}

object ConverterToJson {
  def convertToJson[T](value: T)(implicit converter: ConverterType[T]): String = converter.convert(value)
  def apply[T](implicit converter: ConverterType[T]): ConverterType[T] = converter
}

trait ConvertToObject[T] {
  def convertToJsonObject(value: T): JsonObject
}

object ConverterToJsonObject {
  def convertToJson[T](value: T)(implicit converterToObj: ConvertToObject[T]): JsonObject = converterToObj.convertToJsonObject(value)
  def apply[T](implicit converter: ConvertToObject[T]): ConvertToObject[T] = converter
}