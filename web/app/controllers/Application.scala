package controllers

import play.api._
import play.api.mvc._
import models.Tables
import play.api.db.slick.{ HasDatabaseConfig, DatabaseConfigProvider }
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

object Application extends Controller with HasDatabaseConfig[JdbcProfile] {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  def index = Action.async { implicit request =>
    val q = Tables.Users
    val usersF: Future[Seq[Tables.UsersRow]] = db.run(q.result)
    usersF.map(user => Ok(views.html.index(user)))
  }

}
