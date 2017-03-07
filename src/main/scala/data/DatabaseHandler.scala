package data

import java.io.File
import java.nio.file.{Files, Paths}
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

/**
  * Created by USER on 07/03/2017.
  */
class DatabaseHandler {
  private final val dataDir: File = new File("data")
  private val connection: Connection = getConnection()

  //Club members
  createTable("archer", Map(
    "id" -> "integer PRIMARY KEY AUTOINCREMENT",
    "forename" -> "varchar(255)",
    "surname" -> "varchar(255)",
    "handicap" -> "integer"
  ))

  //Round scores storage
  createTable("portsmouth", Map(
    "archerID" -> "integer PRIMARY KEY",
    "score" -> "integer"
  ))

  //badge claimed storage

  //Round stats sotrage
  createTable("rounds", Map(
    "roundName" -> "varchar(50)",
    "distance" -> "ARRAY"
  ))

  private def getConnection(): Connection = {
    if (!dataDir.exists()){
      dataDir.mkdir()
    }
    Class.forName("org.sqlite.JDBC")
    return DriverManager.getConnection("jdbc:sqlite:data/test.db")
  }

  private def createTable(tableName: String, columns: Map[String, String]): Unit = {
    var query: String = "CREATE TABLE IF NOT EXISTS "
    query += tableName + " ("
    for (col: (String, String) <- columns){
      query += s"${col._1} ${col._2}, "
    }
    query = query.substring(0, query.length-2) + ");"
    println(query)
    executeStatement(query)
  }

  def executeStatement(statement: String, arguments: List[String] = List(), search: Boolean = false): Any ={
    try{
      var preparedStatement: PreparedStatement = connection.prepareStatement(statement, arguments.toArray[String])
      if (search){
        return preparedStatement.executeQuery()
      }
      return preparedStatement.execute()
    }catch{
      case e: Exception => e.printStackTrace()
    }
  }
}
