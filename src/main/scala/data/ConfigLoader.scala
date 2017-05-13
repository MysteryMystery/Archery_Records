package data

import java.io.{File, FileInputStream, FileWriter, StringWriter}
import java.util

import ArcheryRecords.logger
import org.yaml.snakeyaml.nodes.Tag
import org.yaml.snakeyaml.{DumperOptions, Yaml}

import scala.beans.BeanProperty
import scala.collection.mutable
import scala.reflect.io.Path

/**
  * Created by James on 11/03/2017.
  */
class ConfigLoader extends {
  val fileName: String = "data/config.yml"
  var yaml: Yaml = new Yaml()

  init()

  val stream: FileInputStream = new FileInputStream(new File(fileName))
  val dumperOptions: DumperOptions = setupDumpOptions()

  var data: util.HashMap[String, Any] = load()

  def init(): Unit = {
    val confFile: File = new File(fileName)
    if (!confFile.exists() || confFile.length() == 0){
      writeDefaults
    }
  }

  def writeDefaults(): Unit = {
    println(" -- Write Defaults -- ")
    var toDump: util.HashMap[String, Any] = new util.HashMap[String,Any]()
    toDump.put("fullscreen", true)
    toDump.put("screensize", "700x500")
    toDump.put("debug", true)
    dump(toDump)
  }

  def load(): util.HashMap[String, Any] = {
    var data: util.HashMap[String, Any] = yaml.load(stream).asInstanceOf[util.HashMap[String, Any]]
    if (/*getDebug*/true){
      logger.log(logger.DEBUG, this.getClass.toString, s"Config Settings: $data")
      logger.log(logger.DEBUG, this.getClass.toString, s"Config Keys: ${data keySet()}")
    }
    data
  }

  def dump(data: Any): Unit ={
    yaml.dump(data, new FileWriter(fileName))
  }

  def setupDumpOptions(): DumperOptions = {
    var options: DumperOptions = new DumperOptions
    options
  }

  def get(configValue: String): Any = {
    data.get(configValue)
  }

  def getDebug: Boolean = {
    data.get("debug").asInstanceOf[Boolean]
  }

  def getScreenSize: String = {
    data.get("screensize").asInstanceOf[String]
  }
}
