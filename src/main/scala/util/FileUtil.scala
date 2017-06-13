package util

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Project: Archery_Records
  * Package: util
  * Created by MysteryMystery under the MIT Liscense.
  */
object FileUtil {
  def generateUniqueFileName(extension: String): String = {
    val now = new Date()
    val format = new SimpleDateFormat("yyyy_MMMMM_dd_kk_mm_ss")
    var x = format.format(now)
    x += s".${extension}"
    x
  }
}
