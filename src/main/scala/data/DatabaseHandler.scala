package data


import java.io.File
import java.nio.file.{Files, Paths}
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

import exception.CustomDatabaseExeption

import scala.collection.mutable.ListBuffer

import ArcheryRecords.{logger, debug}
/**
  * Created by USER on 07/03/2017.
  */
class DatabaseHandler {
  private final val dataDir: File = new File("data")
  private val connection: Connection = getConnection

  connection.setAutoCommit(true)

  //User accounts
  createTable("account", Map(
    "id" -> "INTEGER PRIMARY KEY AUTOINCREMENT",
    "username" -> "varchar(255) COLLATE NOCASE",
    "password" -> "varchar(255)"
  ))
//"CREATE TABLE IF NOT EXISTS ACCOUNT(id INTEGER PRIMARY KEY, username VARCHAR(255) COLLATE NOCASE, password VARCHAR(256))"

  //Club members
  createTable("archer", Map(
    "id" -> "integer PRIMARY KEY AUTOINCREMENT",
    "forename" -> "varchar(255)",
    "surname" -> "varchar(255)",
    "housenumber" -> "varchar(20)",
    "postcode" -> "varchar(7)",
    "indoorclassification" -> "varchar(1)",
    "outdoorclassification" -> "varchar(16)"
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

  private def getConnection: Connection = {
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
    if (debug){
      logger.log(logger.DEBUG, this.getClass.getSimpleName, query)
    }
    executeStatement(query)
  }

  //Not like python - learnt the hard way
  def executeStatement(statement: String, arguments: List[String] = List(), search: Boolean = false): ResultSet ={
    try{
      var preparedStatement: PreparedStatement = connection.prepareStatement(statement, arguments.toArray[String])
      if (search){
        return preparedStatement.executeQuery()
      }
      preparedStatement.execute()
    }catch{
      case e: Exception => e.printStackTrace()
    }
    null
  }

  def hasAccount: Boolean = {
    try {
      var preparedStatement: PreparedStatement = connection.prepareStatement("SELECT username, password FROM ACCOUNT")
      if (preparedStatement.executeQuery().next()) {
        return true
      }
    }
    catch {
      case e: Exception => e.printStackTrace()
    }
    false
  }

  def insert(table: String, column_value: Map[String, Any]): Unit = {
    var columns: Iterable[String] = column_value.keys
    var values: ListBuffer[Any] = ListBuffer()
    for (key: String <- columns) {
      values.append(column_value.get(key).toString)
    }
    var query: String = s"insert into ${table}(${columns.mkString(", ")}) values ("
    for (value: Any <- values) {
      query += "?,"
    }
    query = query.substring(0, query.length - 1) + ");"
    println(query)
    println(values)

    var preparedStatement: PreparedStatement = connection.prepareStatement(query)
    preparedStatement = populatePreparedStatementValues(preparedStatement, /*values.toList*/ column_value.values.toArray.toList)
    preparedStatement.execute()
  }

  def populatePreparedStatementValues(preparedStatement: PreparedStatement, values: List[Any]): PreparedStatement={
    var counter: Int = 1
    for (value: Any <- values){
      value match {
        case _: String =>
          preparedStatement.setString(counter, value.asInstanceOf[String])
        case _: Int =>
          preparedStatement.setInt(counter, value.asInstanceOf[Int])
        case _ =>
          throw CustomDatabaseExeption(s"Not catered for the datatype ${value.getClass} in populatePreparedStatementValues match statement.")
      }
      counter += 1
    }
    preparedStatement
  }

  def getAccount(username: String, password: String): ResultSet ={
    var preparedStatement: PreparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE username = ? AND password = ?;")
    preparedStatement.setString(1, username)
    preparedStatement.setString(2, password)
    preparedStatement.executeQuery()
  }
}