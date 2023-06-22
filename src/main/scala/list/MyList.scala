package list

sealed abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def :+: [B >: A](x: B): MyList[B] = new :+: (x, this)


}

final case class :+:[A] (head: A, tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false
}
