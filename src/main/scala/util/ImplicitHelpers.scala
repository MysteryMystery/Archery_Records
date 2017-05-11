package util

import scala.util.Try
import scalafx.scene.layout.{GridPane, Region}

/**
  * Project: Archery_Records
  * Package: 
  * Created by MysteryMystery under the MIT Liscense.
  */
object ImplicitHelpers {
  implicit class PackageHelpers(val string: String) extends AnyVal{
    def isAllDigits(): Boolean = {
      Try(string.toDouble).isSuccess
    }

    def isTime(): Boolean = {
      var sections: Array[String] = string.split(":")
      if (sections.length > 3){
        return false
      }
      for (section <- sections){
        if (section.length != 2 || !section.isAllDigits()){
          return false
        }
      }
      true
    }

    def isDate(): Boolean = {
      var sections: Array[String] = string.split("-")

      if(!(sections(0).length == 4 && sections(1).length == 2 && sections(2).length == 2)){
        return false
      }
      for (section <- sections){
        if(!section.isAllDigits()){
          return false
        }
      }
      true
    }

    def isPostCode(): Boolean = {
      string.matches("^[a-zA-Z]{2}\\d\\d \\d[a-zA-Z]{2}")
    }
  }

  implicit class PaneHelper(val string: GridPane) extends GridPane{
    def addInRow(startingColumn:Int, row:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
      var col = startingColumn
      for(component: Region <- components){
        add(component, col, row)
        col += 1
      }
    }

    def addInCol(column:Int, startingrow:Int, columnSpan:Int, rowSpan:Int, components: Region*): Unit ={
      var row = startingrow
      for(component: Region <- components){
        add(component, column, row)
        row += 1
      }
    }
  }
}
