package acme.weaverissue

import cats.effect.Resource
import monix.eval.Task
import weaver.monixcompat.{GlobalRead, MutableTaskSuite}

class OtherSharingSuite(global: GlobalRead)
    extends MutableTaskSuite {
  type Res = String

  def sharedResource: Resource[Task, String] =
    global.getOrFailR[String]()

  test("oops, forgot something here")(sharedString => Task(expect(sharedString == "hello world!")))
}
