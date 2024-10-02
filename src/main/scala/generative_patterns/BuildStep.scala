package generative_patterns

sealed trait BuildStep

sealed trait HasShape extends BuildStep

sealed trait HasColor extends BuildStep

sealed trait HasName extends BuildStep



//case class Button(
//                   shape: String = "n/a",
//                   color: String = "n/a",
//                   btnTxt: String = ""
//                 ) {
//  require(shape != "n/a", "Shape must be specified")
//  require(color != "n/a", "Color must be specified")
//}
//
//class ButtonBuilder[PassedStep <: BuildStep] private(
//                                                      var shape: String,
//                                                      var color: String,
//                                                      var btnTxt: String
//                                                    ) {
//  protected def this() = this("n/a", "n/a", "")
//
//  protected def this(btn: ButtonBuilder[_]) = this(
//    btn.shape,
//    btn.color,
//    btn.btnTxt
//  )
//
//  def setShape(shape: String): ButtonBuilder[HasShape] = {
//    this.shape = shape
//    new ButtonBuilder[HasShape](shape, color, btnTxt)
//  }
//
//  def setColor(color: String)(implicit ev: PassedStep =:= HasShape): ButtonBuilder[HasColor] = {
//    this.color = color
//    new ButtonBuilder[HasColor](shape, color, btnTxt)
//  }
//
//  def setBtnTxt(btnTxt: String)(implicit ev: PassedStep =:= HasColor): ButtonBuilder[HasName] = {
//    this.btnTxt = btnTxt
//    new ButtonBuilder[HasName](shape, color, btnTxt)
//  }
//
//  def build(): Button = {
//    println(s"-$shape- -$color- button -$btnTxt- is created")
//    new Button(shape, color, btnTxt)
//  }
//}
//
//object ButtonBuilder {
//  def apply() = new ButtonBuilder[BuildStep]()
//}

