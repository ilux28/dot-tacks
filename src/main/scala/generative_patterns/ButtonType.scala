package generative_patterns

sealed trait ButtonType
case object ElevatedButton extends ButtonType
case object TextButton extends ButtonType

sealed trait Button {
  val buttonType: ButtonType
  def press(): Unit = {
    println(s"Button -${buttonType.toString.replace("Button", "")} Button- is pressed")
  }
}

case class ElevatedButton(icon: String) extends Button {
  override val buttonType: ButtonType = ElevatedButton
}

case class TextButton() extends Button {
  override val buttonType: ButtonType = TextButton
}

trait ButtonComponent {
  def drawButton(icon: String): Button
}

class ElevatedButtonComponent extends ButtonComponent {
  override def drawButton(icon: String): Button = {
    if (icon.isEmpty) println("icon is not specified")
    ElevatedButton(icon)
  }
}

class TxtButtonComponent extends ButtonComponent {
  override def drawButton(icon: String): Button = TextButton()
}

object Main {
  def main(args: Array[String]): Unit = {
    val elevatedButton = new ElevatedButtonComponent().drawButton("My Icon")
    elevatedButton.press()

    val textButton = new TxtButtonComponent().drawButton("")
    textButton.press()
  }
}
