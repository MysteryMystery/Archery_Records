package data

import java.io.{FileInputStream, FileOutputStream}

import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Project: Archery_Records
  * Package: data
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
  EXAMPLES AT https://poi.apache.org/spreadsheet/examples.html
 */
/*
  TODO ->

  private var sheet = List[List[String]] of 100 lists of 26 length (a-z)
  def setCell(value: OBject, cell: String) where cell in [A-Z][1-100] etc
  makes building the sheet a bit easier
 */

class MSExcelHandler {
  private val STORAGEFILE = "data/data.xls"
  private val handicapSheet = "Handicaps"
  private var sheets: mutable.LinkedHashMap[String, SheetData] = new mutable.LinkedHashMap[String, SheetData]()
  private val wb: XSSFWorkbook = new XSSFWorkbook()

  newSheet("Handicaps")
  editCell("Handicaps", "Blah", "A1")

  def saveWorkbook(): Unit = {
    for (sheetName <- sheets.keys){
      val sheet: Sheet = wb.createSheet(sheetName)
      val styles = createStyles(wb)

      sheet setHorizontallyCenter true
      sheet setFitToPage true
      val printSetup: PrintSetup =  sheet.getPrintSetup
      printSetup setLandscape true

      sheet setAutobreaks true
      printSetup setFitHeight 1.toShort
      printSetup setFitWidth 1.toShort

      var rowNum = 0
      for (row <- sheets.get(sheetName).get.getData){
        val sheetRow: Row = sheet.createRow(rowNum)
        rowNum += 1
        for (c <- row.indices){
          var cell: Cell = sheetRow.createCell(c)
          cell.setCellValue(row(c))
        }
      }
    }

    var out: FileOutputStream = null
    if (wb.isInstanceOf[XSSFWorkbook]){
      out = new FileOutputStream(STORAGEFILE + "x")
    }else{
      out= new FileOutputStream(STORAGEFILE)
    }
    wb.write(out)
    out.close()
    wb.close()
  }

  def newSheet(sheetName: String): Unit = {
    sheets.put(sheetName, new SheetData())
  }

  def editCell(sheetName: String, value: String, coordinate: String): Unit = {
    sheets(sheetName).editCell(value, coordinate)
    saveWorkbook()
  }

  def createStyles(wb: XSSFWorkbook): Map[String, CellStyle] = {
    val toReturn: mutable.Map[String, CellStyle] = mutable.HashMap[String, CellStyle]()
    val dataformat: DataFormat = wb.createDataFormat()

    var style: CellStyle = wb.createCellStyle()
    var headerFont: Font = wb.createFont()
    headerFont.setBold(true)
    style.setAlignment(HorizontalAlignment.CENTER)
    style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex)
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND)
    style.setFont(headerFont)
    toReturn += ("header"->style)
    toReturn.toMap
  }

  private class SheetData(){
    private var data = ListBuffer[ListBuffer[String]]()

    for (y <- 0 to 99){
      var row = ListBuffer[String]()
      for (x <- 0 until 26){
        row += null
      }
      data += row
    }

    def getData: List[List[String]] = data.map((r) => r.toList).toList

    def getRow(row: Int): List[String] = data(row).toList

    def editCell(value: String, coordinate: String): Unit = {
      val listToGet = coordinate(1).toInt - 49
      val positionInInnerList = convertLetterToAlphabetPosition(coordinate(0))

      println(positionInInnerList)

      data(listToGet)(positionInInnerList) = value
      println(data(listToGet)(positionInInnerList))
    }

    def convertLetterToAlphabetPosition(letter: Char): Integer = {
      // 65 - 90 -> uppercase
      // 97 - 122 -> lowercase
      letter.toUpper - 65
    }
  }

}
