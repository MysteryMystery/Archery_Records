package web

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}

import com.google.gson._
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper

/**
  * Project: Archery_Records
  * Package: web
  * Created by MysteryMystery under the MIT Liscense.
  */

/*

    USES ONLINE API: http://postcodes.io/

 */

class PostCode {
  val baseURL: String = "http://api.postcodes.io/postcodes/"

  def getPostcodeDetails(postcode: String): Unit = {
    var query: String = baseURL + postcode
    var response: BufferedReader = sendGetRequest(query)
    var data: String = Stream.continually(response.readLine()).takeWhile(_ != null).mkString("\n")
    var parseData: java.util.Map[String, Any]  = new Gson() fromJson(data, classOf[java.util.Map[String, Any]])
  }

  def sendGetRequest(url: String): BufferedReader = {
    var connection: HttpURLConnection = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
    connection.setRequestMethod("GET")
    connection.setRequestProperty("User-Agent", "Mozilla/5.0")

    new BufferedReader(new InputStreamReader(connection.getInputStream))
  }

  def getStreetName(): String = {
    "NOT YET IMPLEMENTED"
  }
}

