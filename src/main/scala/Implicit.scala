import scala.util.Try

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
}
