package data

import java.io.{FileInputStream, FileOutputStream}

import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.collection.immutable.HashMap
import scala.collection.mutable

/**
  * Project: Archery_Records
  * Package: data
  * Created by MysteryMystery under the MIT Liscense.
  */

/*
  EXAMPLES AT https://poi.apache.org/spreadsheet/examples.html
 */
class MSExcelHandler {
  private val STORAGEFILE = "data/data.xls"
  private val handicapSheet = "Handicaps"

  private val handicapsTitles: List[String] = List("ROUNDNAME", "blah", "blahdiblah")

  def saveWorkbook(wb: Workbook): Unit = {
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

  def setupSheet(): Unit = {
    val workbook: XSSFWorkbook = new XSSFWorkbook()
    val sheet: Sheet = workbook.createSheet(handicapSheet)
    val styles = createStyles(workbook)

    sheet setHorizontallyCenter true
    sheet setFitToPage true
    val printSetup: PrintSetup =  sheet.getPrintSetup
    printSetup setLandscape true

    sheet setAutobreaks true
    printSetup setFitHeight 1.toShort
    printSetup setFitWidth 1.toShort

    val headerRow: Row = sheet.createRow(0)
    headerRow setHeightInPoints 12.75f
    for (i <- handicapsTitles.indices){
      var cell: Cell = headerRow.createCell(i)
      cell.setCellValue(handicapsTitles.apply(i))
      cell.setCellStyle(styles.get("header").get)
    }
    saveWorkbook(workbook)
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

}
