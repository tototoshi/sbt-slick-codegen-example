package controllers

import play.api._
import play.api.mvc._
import models.Tables
import play.api.db.slick.Database
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    Database("default").withSession { implicit session =>
      Ok(Tables.Users.list.toString)
    }
  }

}
