package controllers

import org.apache.pekko.actor.ActorSystem
import org.scalatest.funsuite.AnyFunSuite
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ApplicationTest extends AnyFunSuite {

  private val app = new GuiceApplicationBuilder().build()

  private implicit val actorSystem: ActorSystem = app.actorSystem

  test("HomeController#index") {
    running(app) {
      val homeController = app.injector.instanceOf[Application]
      val request = FakeRequest()
      val result = call(homeController.index, request)
      assert(contentAsString(result).contains("<td>tototoshi</td>"))
    }
  }

}
