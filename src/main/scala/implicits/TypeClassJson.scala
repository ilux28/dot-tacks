package implicits

object TypeClassJson extends App {

  implicit object JsonStringConverter extends ConverterType[JsonString] {
    override def convert(value: JsonString): String = s""""${value.str}""""
  }

  implicit object JsonIntegerConverter extends ConverterType[JsonInteger] {
    override def convert(value: JsonInteger): String = s"""${value.number}"""
  }

  implicit object JsonArrayConverter extends ConverterType[JsonArray] {
    override def convert(array: JsonArray): String = s"""[${array.list.map({
      case strObj: JsonString => ConverterToJson[JsonString].convert(strObj)
      case intObj: JsonInteger => ConverterToJson[JsonInteger].convert(intObj)
    }).mkString(",")}]"""
  }

  implicit object JsonObjectConverter extends ConverterType[JsonObject] {
    override def convert(obj: JsonObject): String = {
      obj.map.map {case (key, value) => {
        val convertValue: String = value match {
          case arrayObj: JsonArray => ConverterToJson[JsonArray].convert(arrayObj)
          case strObj: JsonString => ConverterToJson[JsonString].convert(strObj)
          case intObj: JsonInteger => ConverterToJson[JsonInteger].convert(intObj)
        }
        s"""{"$key": $convertValue}"""
      }
      }.mkString(",")
    }
  }

//  val customer = Customer("alice@mail.com", "Alice")
//
//  val order = Order(customer, List(
//    Item(1, "first item"),
//    Item(2, "second item"),
//  ))

  val data = JsonObject(Map(
    "name" -> JsonString("Bob"),
    "items" -> JsonArray(
      List(
        JsonInteger(1),
        JsonString("Stuff to buy"))
    )
  ))

  println(ConverterToJson[JsonObject].convert(data))

}

case class  JsonString(str: String)
case class  JsonInteger(number: Int)
case class  JsonArray(list: List[AnyRef])
case class  JsonPrimitive(value: AnyVal)
case class  JsonObject(map: Map[String, AnyRef])

//case class Customer(email: String, name: String)
//case class Item(id: Int, description: String)
//case class Order(customer: Customer, items: List[Item])

trait ConverterType[T] {
  def convert(value: T): String
}

object ConverterToJson {
  def convertToJson[T](value: T)(implicit converter: ConverterType[T]): String = converter.convert(value)
  def apply[T](implicit converter: ConverterType[T]): ConverterType[T] = converter
}