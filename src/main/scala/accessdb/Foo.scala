package accessdb

import cats.Monad
import cats.effect.IO

final case class FooId(value: Int) extends AnyVal

final case class Foo(id: FooId, references: List[FooId], data: String)

trait FooRepository[F[_]] {
  def read(ids: List[FooId]): F[Set[Foo]]
}

object Foo {
  def readClosure[F[_] : Monad](repo: FooRepository[IO], ids: List[FooId]): IO[Set[Foo]] = {
    for {
      headLevelResult <- repo.read(ids)
      lastLevelResult <- readClosure(repo, headLevelResult.map(_.id).toList)
    } yield lastLevelResult
  }
}
