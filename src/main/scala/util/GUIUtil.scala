package util

import data.{ConfigLoader, DatabaseHandler}

import scala.sys.process.ProcessBuilder.URLBuilder
import scalafx.geometry.{Insets, Pos, Rectangle2D}
import scalafx.scene.Scene
import scalafx.scene.layout.{GridPane, Region}
import scalafx.scene.text.Text
import scalafx.stage.Screen


/**
  * Created by James on 08/03/2017.
  */
trait GUIUtil {
  var databaseHandler: DatabaseHandler
  var confLoader: ConfigLoader

  val screenSize: Rectangle2D = Screen.primary.visualBounds

  var gridPane: GridPane = generateGridPane

  var sceneTitle: Text

  def getScene: Scene = {
    sceneTitle.setId("sceneTitle")
    gridPane.add(sceneTitle, 0, 0, 2, 1)

    // if (fullscreen): width = max, height = max else: look in config

    var scene: Scene = new Scene(gridPane, screenSize.getWidth , screenSize.getHeight)
    //var scene: Scene = new Scene()

    scene.stylesheets.add(getClass.getResource("/styles.css").toExternalForm)
    scene
  }

  /*
   Scene scene = new Scene(gridPane, StockControl.screenSize.getWidth(), StockControl.screenSize.getHeight());
        scene.getStylesheets().add(StockControl.class.getResource(StockControl.styleSheetPath).toExternalForm());
   */

  def generateGridPane: GridPane = {
    val pane: GridPane = new GridPane
    pane.setAlignment(Pos.CENTER)
    pane.setHgap(10)
    pane.setVgap(10)
    pane.setPadding(Insets(25))
    pane
  }

  def makeMaxWidth(components: Region*): Unit ={
    for (i: Region <- components){
      i.setMaxWidth(Double.MaxValue)
    }
  }

  def addInRow(startingColumn:Int, row:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
    var col = startingColumn
    for(component: Region <- components){
      gridPane.add(component, col, row)
      col += 1
    }
  }

  def addInCol(column:Int, startingrow:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
    var row = startingrow
    for(component: Region <- components){
      gridPane.add(component, column, row)
      row += 1
    }
  }
}
