package controllers

import models.Tables.UsersRow
import org.joda.time.DateTime
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import models.Tables
import play.api.db.slick.{ HasDatabaseConfig, DatabaseConfigProvider }
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

case class UserForm(name: String)

object Application extends Controller with HasDatabaseConfig[JdbcProfile] {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  def index = Action.async { implicit request =>
    val q = Tables.Users
    val usersF: Future[Seq[Tables.UsersRow]] = db.run(q.result)
    usersF.map(user => Ok(views.html.index(user)))
  }

  val userForm = Form(
    mapping(
      "name" -> nonEmptyText
    )(UserForm.apply)(UserForm.unapply)
  )

  def create = Action { implicit request =>
    Ok(views.html.create(userForm))
  }

  def createPost = Action { implicit request =>
    def handleError(form: Form[UserForm]): Result = {
      BadRequest(views.html.create(form))
    }

    def handleSuccess(form: UserForm): Result = {
      val q = Tables.Users += UsersRow(0, form.name, DateTime.now)
      db.run(q)
      Redirect(routes.Application.index)
    }

    userForm.bindFromRequest.fold(handleError, handleSuccess)
  }

}
