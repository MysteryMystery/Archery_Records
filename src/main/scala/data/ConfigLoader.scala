package data

import java.io.{File, FileInputStream, FileWriter, StringWriter}
import java.util
import java.util.LinkedHashMap

import org.yaml.snakeyaml.nodes.Tag
import org.yaml.snakeyaml.{DumperOptions, Yaml}

import scala.beans.BeanProperty
import scala.collection.mutable

/**
  * Created by James on 11/03/2017.
  */
class ConfigLoader {
  val fileName: String = "src/main/resources/config.yml"
  var yaml: Yaml = new Yaml()

  init()

  val stream: FileInputStream = new FileInputStream(new File(fileName))
  val dumperOptions: DumperOptions = setupDumpOptions()

  var data: util.LinkedHashMap[String, util.LinkedHashMap[String, Any]] = load()

  def init(): Unit = {
    val confFile: File = new File(fileName)
    if (!confFile.exists()){
      confFile.createNewFile()
      writeDefaults
    }
    else if (confFile.length() == 0){
      writeDefaults
    }
  }

  def writeDefaults(): Unit = {
    println(" -- Write Defaults -- ")
    var toDump: util.LinkedHashMap[String, util.LinkedHashMap[String, Any]] = new util.LinkedHashMap[String, util.LinkedHashMap[String, Any]]()
    var innerLevel: util.LinkedHashMap[String, Any] = new util.LinkedHashMap[String, Any]

    innerLevel.put("fullscreen", true)
    innerLevel.put("debug", false)

    toDump.put("settings", innerLevel)

    dump(toDump)
  }

  def load(): util.LinkedHashMap[String, util.LinkedHashMap[String, Any]] = {
    var data: util.LinkedHashMap[String, util.LinkedHashMap[String, Any]] = yaml.load(stream).asInstanceOf[util.LinkedHashMap[String, util.LinkedHashMap[String, Any]]]
    data
  }

  def dump(data: Any): Unit ={
    yaml.dump(data, new FileWriter(fileName))
  }

  def setupDumpOptions(): DumperOptions = {
    var options: DumperOptions = new DumperOptions
    if (debug) {
      options.setDefaultFlowStyle(DumperOptions.FlowStyle.FLOW)
      println(options.getDefaultFlowStyle)
      println(options.getLineBreak)
    }
    options
  }

  def get(configValue: String): Any = {
    //yaml.load(stream).asInstanceOf[util.LinkedHashMap[String, util.LinkedHashMap[String, Any]]].get("settings").get("fullscreen")
    data.get("settings").get(configValue)
  }

  def debug: Boolean = {
    true
  }
}
