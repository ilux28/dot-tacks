package systemtypes

object TypeMembers extends App {
//  val res = new IntRecord {
//
//    override def extract[RecordType <: Int](key: RecordType): SafeType = key
//  }
  //TODO required revision
}

trait SafeType {
  type Key
}

trait Record[T] extends SafeType {
  type Key = T // заставили тип быть строго заданного типа
//  def extract[Record[T]](key: RecordType extends SafeType): SafeType
}

trait IntRecord extends Record[Int]
trait StringRecord extends Record[String]