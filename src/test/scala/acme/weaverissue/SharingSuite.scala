package acme.weaverissue

import cats.effect.Resource
import monix.eval.Task
import weaver.monixcompat.{GlobalRead, MutableTaskSuite}

class SharingSuite(global: GlobalRead) extends MutableTaskSuite {
  type Res = String
  def sharedResource: Resource[Task, String] =
    global.getOrFailR[String]()

  test("a stranger, from the outside ! ooooh")(sharedString => Task(expect(sharedString == "hello world!")))
}
