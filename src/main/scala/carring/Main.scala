package carring

object Main {

  val gt: IntPairPred = _ > _
  val ge: IntPairPred = _ >= _
  val lt: IntPairPred = _ < _
  val le: IntPairPred = _ <= _
  val eq: IntPairPred = _ == _

  type EmailFilter = Email => Boolean
  type IntPairPred = (Int, Int) => Boolean

  /**
   * This fiction is a partial apply
   * @param pred
   * @param n
   * @param email
   * @return
   */
  def sizeConstraint(pred: IntPairPred, n: Int, email: Email): Boolean = pred(email.text.length, n)

/**
   * This fiction is a carring
   * @param pred
   * @param n
   * @param email
   * @return
   */
//def sizeConstraint(pred: IntPairPred)(n: Int)(email: Email): Boolean =
//  pred(email.text.size, n)



  val minimumSize: (Int, Email) => Boolean = sizeConstraint(ge, _: Int, _: Email)
  val maximumSize: (Int, Email) => Boolean = sizeConstraint(le, _: Int, _: Email)

  val constr20: (IntPairPred, Email) => Boolean = sizeConstraint(_: IntPairPred, 20, _: Email)
  val constr30: (IntPairPred, Email) => Boolean = sizeConstraint(_: IntPairPred, 30, _: Email)

  val sizeConstraintFn: (IntPairPred, Int, Email) => Boolean = sizeConstraint

  val min20: EmailFilter = minimumSize(20, _: Email)
  val max20: EmailFilter = maximumSize(20, _: Email)

  val min20Other: EmailFilter = constr20(ge, _: Email)
  val max20Other: EmailFilter = constr20(le, _: Email)

  def main(args: Array[String]): Unit = {

  }

}
case class Email(subject: String, text: String, sender: String, recipient: String)