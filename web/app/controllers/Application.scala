package controllers

import play.api._
import play.api.mvc._
import models.Tables
import slick.driver.H2Driver.api._
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Application extends Controller {

  def index = Action.async {
    val db = Database.forConfig("db.default")
    val dbio = Tables.Users.filter(_.id === 1).result
    db.run(dbio).map(result => Ok(result.toString))
  }

}
