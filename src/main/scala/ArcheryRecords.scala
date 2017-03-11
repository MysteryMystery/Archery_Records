import data.{ConfigLoader, DatabaseHandler}
import scene.form.login.FirstUserRegistration
import util.GUIUtil

import scala.beans.BeanProperty
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Rectangle2D}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.Image
import scalafx.scene.layout.HBox
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.scene.paint.Color._
import scalafx.stage.Screen

/**
  * Created by USER on 07/03/2017.
  */
/*class ArcheryRecords{
  private val databaseHandler: DatabaseHandler = new DatabaseHandler
  private val confLoader: ConfigLoader = new ConfigLoader

  def getDatabaseHandler(): DatabaseHandler = {
    databaseHandler
  }

  def getConfLoader(): ConfigLoader = {
    confLoader
  }

}*/
class ArcheryRecords

package object ArcheryRecords {
  var databaseHandler: DatabaseHandler = new DatabaseHandler
  var confLoader: ConfigLoader = new ConfigLoader

  /*stage = new PrimaryStage
  stage.setTitle("Archery Records")

  if (!databaseHandler.hasAccount){
    //set first login form
    var form = new FirstUserRegistration(databaseHandler)
    stage.scene = form.getScene
  }
  else {
    // normal login
  }
  //stage.fullScreen = true
  stage.icons.add(new Image("ArcheryRecordsIcon.png"))*/
}

object run extends JFXApp{
  var databaseHandler = ArcheryRecords.databaseHandler
  val confLoader = ArcheryRecords.confLoader

  stage = new PrimaryStage
  stage.setTitle("Archery Records")

  if (!databaseHandler.hasAccount){
    //set first login form
    var form = new FirstUserRegistration(databaseHandler, confLoader)
    stage.scene = form.getScene
  }
  else {
    // normal login
  }
  //stage.fullScreen = true
  stage.icons.add(new Image("ArcheryRecordsIcon.png"))
}