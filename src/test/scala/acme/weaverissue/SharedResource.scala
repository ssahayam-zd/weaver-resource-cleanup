package acme.weaverissue

import monix.eval.Task
import weaver._
import weaver.monixcompat.{GlobalResource, GlobalWrite}
import cats.effect.Resource

object SharedResources extends GlobalResource {
  def sharedResources(global: GlobalWrite): Resource[Task, Unit] = {

    val acquire = {
      Task(println("=====> acquiring shared resource")) >> Task.pure("hello world!")
    }

    val release = (s: String) => for {
      _ <- Task(println(s"======> About to release shared resource: $s"))
      _ <- Task(Thread.sleep(10000))
      _ <-  Task(println(s"======> Released shared resource: $s"))
    } yield ()

    for {
      foo <- Resource.make(acquire)(release)
      _ <- global.putR(foo)
    } yield ()
  }
}
