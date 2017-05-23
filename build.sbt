name := "Archery_Records"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.1"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.7.2"
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"
// https://mvnrepository.com/artifact/org.yaml/snakeyaml
libraryDependencies += "org.yaml" % "snakeyaml" % "1.8"
// https://mvnrepository.com/artifact/commons-io/commons-io
libraryDependencies += "commons-io" % "commons-io" % "2.5"
// https://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.0"
// https://mvnrepository.com/artifact/org.apache.poi/poi
libraryDependencies += "org.apache.poi" % "poi" % "3.16-beta2"
// https://mvnrepository.com/artifact/com.lowagie/itext
libraryDependencies += "com.lowagie" % "itext" % "4.2.1"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6"
// https://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.0"
// https://mvnrepository.com/artifact/com.h2database/h2
libraryDependencies += "com.h2database" % "h2" % "1.4.195"



fork := true

//sbt assembly - command to run in sbt console to build standalone