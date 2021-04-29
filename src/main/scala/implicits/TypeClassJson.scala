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
      val jsonResult = obj.map.map {case (key, value) => {
        val convertValue: String = value match {
          case arrayObj: JsonArray => ConverterToJson[JsonArray].convert(arrayObj)
          case strObj: JsonString => ConverterToJson[JsonString].convert(strObj)
          case intObj: JsonInteger => ConverterToJson[JsonInteger].convert(intObj)
        }
        s""""$key":$convertValue"""
      }
      }.mkString(",")
      s"{$jsonResult}"
    }
  }

  val data = JsonObject(Map(
    "name" -> JsonString("Bob"),
    "items" -> JsonArray(
      List(
        JsonInteger(1),
        JsonString("Stuff to buy"))
    )
  ))

  implicit class Stringify(jsonObject: JsonObject) {
    def stringify: String = ConverterToJson[JsonObject].convert(jsonObject)
  }

  val example = """{"name":"Bob","items":[1,"Stuff to buy"]}"""
//  println(data.co)
//  println(ConverterToJson[JsonObject].convert(data))
  println(example)
  println(ConverterToJson[JsonObject].convert(data))
  println(data.stringify)

}

case class  JsonString(str: String)
case class  JsonInteger(number: Int)
case class  JsonArray(list: List[AnyRef])
case class  JsonPrimitive(value: AnyVal)
case class  JsonObject(map: Map[String, AnyRef])

trait ConverterType[T] {
  def convert(value: T): String
}

object ConverterToJson {
  def convertToJson[T](value: T)(implicit converter: ConverterType[T]): String = converter.convert(value)
  def apply[T](implicit converter: ConverterType[T]): ConverterType[T] = converter
}