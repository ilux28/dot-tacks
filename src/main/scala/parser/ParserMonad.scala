package parser

trait ParserMonad[F[_]] {
  def pure[A](x: A): F[A]
  def flatMap[A, B](fx: F[A])(f: A => F[B]): F[B]
  def map[A, B](fx: F[A])(f: A => B): F[B]
}

