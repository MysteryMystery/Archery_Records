import data.{ConfigLoader, DatabaseHandler}
import exception.CustomDatabaseExeption
import scene.form.login.{FirstUserRegistration, NormalLogin}
import util.{GUIUtil, Logger}

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

class ArcheryRecords

package object ArcheryRecords {
  var logger: Logger = new Logger
  var databaseHandler: DatabaseHandler = new DatabaseHandler
  var confLoader: ConfigLoader = new ConfigLoader

  var debug: Boolean = confLoader.getDebug
}

object run extends JFXApp{
  var databaseHandler = ArcheryRecords.databaseHandler
  val confLoader = ArcheryRecords.confLoader

  if (ArcheryRecords.debug){
    println("Debug set to true")
  }

  stage = new PrimaryStage
  stage.setTitle("Archery Records")

  if (!databaseHandler.hasAccount){
    //set first login form
    var form = new FirstUserRegistration(databaseHandler, confLoader)
    stage.scene = form.getScene
  }
  else {
    // normal login
    var form = new NormalLogin(databaseHandler, confLoader)
    stage.scene = form.getScene
  }

  //stage.fullScreen = true
  stage.icons.add(new Image("ArcheryRecordsIcon.png"))
}