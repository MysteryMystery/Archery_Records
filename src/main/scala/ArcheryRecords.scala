import ArcheryRecords.getClass
import data.DatabaseHandler
import scene.form.login.FirstUserRegistration
import util.GUIUtil

import scala.beans.BeanProperty
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Rectangle2D}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.scene.paint.Color._
import scalafx.stage.Screen

/**
  * Created by USER on 07/03/2017.
  */
class ArcheryRecords {
}

object ArcheryRecords extends JFXApp{
  val databaseHandler: DatabaseHandler = new DatabaseHandler

  stage = new PrimaryStage
  stage.setTitle("Archery Records")

  if (!databaseHandler.hasAccount){
    //set first login form
    var form = new FirstUserRegistration()
    stage.scene = form.getScene
  }
  else {
    // normal login
  }

  def getDatabaseHandler: DatabaseHandler = databaseHandler

}