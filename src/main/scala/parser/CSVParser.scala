package parser

class CSVParser[T, Src](private val p: Src => (T, Src)) {
  def flatMap[M](f: T => CSVParser[M, Src]): CSVParser[M, Src] =
    CSVParser { src =>
      val (word, rest) = p(src)
      f(word).p(rest)
    }
  def map[M](f: T => M): CSVParser[M, Src] =
    CSVParser { src: Src =>
      val (word, rest) = p(src)
      (f(word), rest)
    }
  def parse(src: Src): T = p(src)._1
}

object CSVParser {
  def apply[T, Src](f: Src => (T, Src)) =
    new CSVParser[T, Src](f)
}