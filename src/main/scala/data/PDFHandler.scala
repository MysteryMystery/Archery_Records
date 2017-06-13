package data

import com.itextpdf.text.pdf.PdfWriter

import scalafx.scene.web.HTMLEditor
import util.FileUtil.generateUniqueFileName

/**
  * Project: Archery_Records
  * Package: data
  * Created by MysteryMystery under the MIT Liscense.
  */
class PDFHandler {

  def write():Unit = {
    println(generateUniqueFileName(PDFHandler.EXTENSION))
  }
}

object PDFHandler {
  val EXTENSION: String = "pdf"
}
