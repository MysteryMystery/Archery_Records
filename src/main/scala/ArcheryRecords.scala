import javafx.application.Application

import data.{ConfigLoader, DatabaseHandler}
import exception.CustomDatabaseExeption
import scene.form.login.{FirstUserRegistration, NormalLogin}
import util.{GUIUtil, Logger}

import scala.beans.BeanProperty
import scalafx.application.{AppHelper, JFXApp}
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
  var primaryStage: JFXApp.PrimaryStage = _

  var debug: Boolean = confLoader.getDebug
}

object run extends JFXApp{

  if (ArcheryRecords.debug){
    ArcheryRecords.logger.log(ArcheryRecords.logger.DEBUG, this.getClass.getSimpleName, "Debug mode: TRUE")
  }

  stage = new PrimaryStage

  ArcheryRecords.primaryStage = stage

  stage.setTitle("Archery Records")

  if (!ArcheryRecords.databaseHandler.hasAccount){
    //set first login form
    var form = new FirstUserRegistration()

  stage.scene = form.getScene
  }
  else {
    // normal login
    var form = new NormalLogin()

  stage.scene = form.getScene
  }

  //stage.fullScreen = true

  stage.icons.add(new Image("ArcheryRecordsIcon.png"))

}