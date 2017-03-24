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

fork := true

//sbt assembly - command to run in sbt console to build standalone