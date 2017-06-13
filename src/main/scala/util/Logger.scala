package util

/**
  * Created by James on 23/03/2017.
  */
class Logger {
  val INFO: LoggerType =  new LoggerType("infologger")
  val DEBUG: LoggerType = new LoggerType("debuglogger")
  val WARN: LoggerType = new LoggerType("warnlogger")

  def log(loggerType: LoggerType, className: String, message: String): Unit = {
    println(s"[${loggerType.toString} - $className] $message")
  }
}

class LoggerType(code: String) {
  val identifier: String = code

  override def toString: String = {
    identifier match {
      case "infologger" => "INFO"
      case "debuglogger" => "DEBUG"
      case "warnlogger" => "WARN"
    }
  }
}
