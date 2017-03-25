package util

import data.{ConfigLoader, DatabaseHandler}

import scala.sys.process.ProcessBuilder.URLBuilder
import scalafx.geometry.{Insets, Pos, Rectangle2D}
import scalafx.scene.Scene
import scalafx.scene.layout.{GridPane, Region}
import scalafx.scene.text.Text
import scalafx.stage.Screen

import ArcheryRecords._


/**
  * Created by James on 08/03/2017.
  */
trait GUIUtil {
  protected var databaseHandler: DatabaseHandler = ArcheryRecords.databaseHandler
  protected var confLoader: ConfigLoader = ArcheryRecords.confLoader

  protected val screenSize: Rectangle2D = Screen.primary.visualBounds

  protected var gridPane: GridPane = generateGridPane

  protected var sceneTitle: Text

  def getScene: Scene = {
    sceneTitle.setId("sceneTitle")
    gridPane.add(sceneTitle, 0, 0, 2, 1)

    // if (fullscreen): width = max, height = max else: look in config
    val height: Double =  if (!confLoader.get("fullscreen").asInstanceOf[Boolean]) confLoader.get("screensize").asInstanceOf[String].split("x")(1).toDouble else screenSize.getHeight
    val width: Double =  if (!confLoader.get("fullscreen").asInstanceOf[Boolean]) confLoader.get("screensize").asInstanceOf[String].split("x")(0).toDouble else screenSize.getWidth
    if(debug){
      logger.log(logger.DEBUG, this.getClass.getSimpleName, s"Screen Width: $width")
      logger.log(logger.DEBUG, this.getClass.getSimpleName, s"Screen Height: $height")
    }

    var scene: Scene = new Scene(gridPane, width, height)
    //var scene: Scene = new Scene()

    scene.stylesheets.add(getClass.getResource("/styles.css").toExternalForm)
    scene
  }

  /*
   Scene scene = new Scene(gridPane, StockControl.screenSize.getWidth(), StockControl.screenSize.getHeight());
        scene.getStylesheets().add(StockControl.class.getResource(StockControl.styleSheetPath).toExternalForm());
   */

  protected def generateGridPane: GridPane = {
    val pane: GridPane = new GridPane
    pane.setAlignment(Pos.CENTER)
    pane.setHgap(10)
    pane.setVgap(10)
    pane.setPadding(Insets(25))
    pane
  }

  protected def makeMaxWidth(components: Region*): Unit ={
    for (i: Region <- components){
      i.setMaxWidth(Double.MaxValue)
    }
  }

  protected def addInRow(startingColumn:Int, row:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
    var col = startingColumn
    for(component: Region <- components){
      gridPane.add(component, col, row)
      col += 1
    }
  }

  protected def addInCol(column:Int, startingrow:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
    var row = startingrow
    for(component: Region <- components){
      gridPane.add(component, column, row)
      row += 1
    }
  }
}
