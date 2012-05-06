package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

object Application extends Controller {

  /**
   * Describes the hello form.
   */
  val examResultsForm = Form(
    tuple(
      "fname" -> nonEmptyText,
      "sname" -> nonEmptyText,
      "y2008" -> number(min = 0, max = 100),
      "y2009" -> number(min = 0, max = 100),
      "y2010" -> number(min = 0, max = 100),
      "y2011" -> number(min = 0, max = 100),
      "y2012" -> number(min = 0, max = 100)))

  def index = Action {
    Ok(html.index(examResultsForm))
  }

  /**
   * Handles the form submission.
   */
  def enterResults = Action { implicit request =>
    examResultsForm.bindFromRequest.fold(formWithErrors =>
      BadRequest(html.index(formWithErrors)),
      {
        case (fname, sname, y2008, y2009, y2010, y2011, y2012) => Ok(html.chart(fname, sname, y2008, y2009, y2010, y2011, y2012))
      })
  }
}