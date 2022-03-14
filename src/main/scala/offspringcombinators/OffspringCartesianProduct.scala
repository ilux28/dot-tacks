package offspringcombinators

object OffspringCartesianProduct extends App {


  /** *
   *
   * @param abc
   * @param n
   * @tparam A
   * @return Cartesian degree
   */
  def pow[A](abc: List[A], n: Int): List[List[A]] =
    if (n == 1) abc.map(List(_))
    else pow(abc, n - 1).flatMap(word => abc.map(ch => word :+ ch))

  /** *
   *
   * @param abc
   * @param n
   * @tparam A
   * @return Generation of all permutations(Генерация всех перестановок)
   */
  def perm[A](list: List[A]): List[List[A]] =
    if (list.length == 1) List(list)
    else list.indices
      .flatMap(k => perm(list.patch(k, Nil, 1)).map(word => list(k) :: word)).toList

  /** *
   * @param list
   * @tparam A
   * @return Generation of all subsets(Генерация всех подмножеств)
   */
  def sub[A](list: List[A]): List[List[A]] =
    if (list.length == 1) List(list)
    else list.indices
      .flatMap(k => perm(list.patch(k, Nil, 1)).map(word => list(k) :: word)).toList
/** *
   *
   * @param abc
   * @param n
   * @tparam A
   * @return Generation of all subsets without order(Генерация всех РАЗБИЕНИЙ)
   */
  def subWithoutOrder[A](list: List[A]): List[List[A]] =
    if (list.length == 1) List(list)
    else list.indices
      .flatMap(k => perm(list.patch(k, Nil, 1)).map(word => list(k) :: word)).toList

  val list = List(0, 1, 2, 3)
  val patch = list.patch(1, Nil, 1)

  val pow1 = pow(List(0, 1), 3)
  println(patch)
}
