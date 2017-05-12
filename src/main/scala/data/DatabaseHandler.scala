package data


import java.io.File
import java.nio.file.{Files, Paths}
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

import exception.CustomDatabaseExeption

import scala.collection.mutable.ListBuffer
import ArcheryRecords.{debug, logger}
import util.archeryspecific.ClassificationRequirements
import util.personspecific.Member

import scala.collection.mutable
/**
  * Created by USER on 07/03/2017.
  */
class DatabaseHandler {
  private final val dataDir: File = new File("data")
  private val connection: Connection = getConnection

  connection.setAutoCommit(true)

  //User accounts
  createTable("account", mutable.LinkedHashMap(
    "id" -> "INTEGER PRIMARY KEY AUTOINCREMENT",
    "username" -> "varchar(255) COLLATE NOCASE",
    "password" -> "varchar(255)"
  ))
//"CREATE TABLE IF NOT EXISTS ACCOUNT(id INTEGER PRIMARY KEY, username VARCHAR(255) COLLATE NOCASE, password VARCHAR(256))"

  //Club members
  createTable("archer", mutable.LinkedHashMap(
    "id" -> "integer PRIMARY KEY AUTOINCREMENT",
    "forename" -> "varchar(255) COLLATE NOCASE",
    "surname" -> "varchar(255) COLLATE NOCASE",
    "housenumber" -> "varchar(20)",
    "postcode" -> "varchar(7)",
    "indoorclassification" -> "varchar(1)",
    "outdoorclassification" -> "varchar(16)"
  ))

  //Round scores storage
  for (i <- ClassificationRequirements.Male.Adult.allRounds){
    createTable(i, mutable.LinkedHashMap(
      "archerID" -> "integer PRIMARY KEY",
      "bowtype" -> "varchar(1)",
      "score" -> "integer"
    ))
  }

  //badge claimed storage

  //Round stats sotrage
  createTable("rounds", mutable.LinkedHashMap(
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

  private def createTable(tableName: String, columns: mutable.LinkedHashMap[String, String]): Unit = {
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

//  //Get resultset -> put all rows into list in form of new Member(fields)
//  def getAllMembers(): List[Member] ={
//    var x: ListBuffer[Member] = ListBuffer[Member]()
//    for (y <- 0 to 15){
//      x.append(new Member)
//    }
//    x.toList
//  }

  def getAllArcherNames(): List[String] = {
    var preparedStatement: PreparedStatement = connection.prepareStatement("SELECT id, forename, surname FROM archer;")
    var rs: ResultSet = preparedStatement.executeQuery()
    var toReturn: ListBuffer[String] =  ListBuffer()
    while (rs.next()){
      toReturn.append(s"${rs.getString(1)}. ${rs.getString(2)} ${rs.getString(3)}")
    }
    toReturn.toList
  }

  def insertArcher(forename: String, surname: String, housenumber: String, postcode: String, indoorClass: String = "", outdoorClass: String = ""): Unit ={
    insert("archer", Map(
      "forename" -> forename,
      "surname" -> surname,
      "housenumber" -> housenumber,
      "postcode" -> postcode,
      "indoorclassification" -> indoorClass,
      "outdoorclassification" -> outdoorClass
    ))
  }

  def getArcher(id: String, forename: String, surname: String, housenumber: String, postcode: String, indoorClass: String, outdoorClass: String): List[Member] = {
    val toReturn: ListBuffer[Member] = ListBuffer()
    var query: String = "SELECT * FROM ARCHER WHERE "
    var args: ListBuffer[String] = ListBuffer()
    if (id != null){
      query += s"id = ? AND "
      args += id
    }
    if (forename != null){
      query += s"forename = ? AND "
      args += forename
    }
    if (surname != null){
      query += s"surname = ? AND "
      args += surname
    }
    if (housenumber != null){
      query += s"housenumber = ? AND "
      args += housenumber
    }
    if (indoorClass != null){
      query += s"indoorclassification = ? AND "
      args += indoorClass
    }
    if (outdoorClass != null){
      query += s"outdoorclassfication = ?"
      args += outdoorClass
    }

    if (query.matches(".+ AND $")){
      var temp = query.split(" ")
      temp.update(temp.length - 1, "")
      query = temp.mkString(" ")
    }
    query += ";"
    System.out.println(query)

    var preparedStatement: PreparedStatement = connection.prepareStatement(query)
    for (i <- 0 until args.length ){
      preparedStatement.setString(i+1, args(i))
    }
    val rs: ResultSet = preparedStatement.executeQuery()
    while (rs.next()){
      toReturn += new Member(
        rs.getString(1),
        rs.getString(2),
        rs.getString(3),
        rs.getString(4),
        rs.getString(5),
        rs.getString(6),
        rs.getString(7)
      )
    }

    System.out.println(args)
    toReturn.toList
  }

  def editArcher(same: mutable.LinkedHashMap[String, String], different:mutable.LinkedHashMap[String, String]):Unit = {
    System.out.println("EDIT")
  }
}