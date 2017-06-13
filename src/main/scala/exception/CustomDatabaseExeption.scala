package exception

/**
  * Created by James on 12/03/2017.
  */
case class CustomDatabaseExeption(message: String = "", cause: Throwable = null) extends Exception(message, cause){

}
