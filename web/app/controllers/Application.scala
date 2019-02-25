package controllers

import javax.inject.Inject
import models.Tables
import models.Tables.UsersRow
import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.i18n.I18nSupport
import play.api.mvc._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

case class UserForm(name: String)

class Application @Inject() (
    cc: ControllerComponents,
    databaseConfigProvider: DatabaseConfigProvider)(implicit e: ExecutionContext)
  extends AbstractController(cc)
  with I18nSupport {

  private val dbConfig = databaseConfigProvider.get[JdbcProfile]

  import dbConfig.profile.api._

  def index = Action.async { implicit request =>
    val q = Tables.Users
    val usersF: Future[Seq[Tables.UsersRow]] = dbConfig.db.run(q.result)
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
      dbConfig.db.run(q)
      Redirect(routes.Application.index)
    }

    userForm.bindFromRequest.fold(handleError, handleSuccess)
  }

}
