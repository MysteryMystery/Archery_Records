import scala.util.Try
import scalafx.scene.layout.{GridPane, Pane, Region}

/**
  * Project: Archery_Records
  * Package: 
  * Created by MysteryMystery under the MIT Liscense.
  */
package object Implicit {
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
  }

  implicit class PaneHelper(val string: String) extends GridPane{
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
