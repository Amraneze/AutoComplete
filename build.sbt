name := "AutoComplete"

version := "0.1"
mainClass in Compile := Some("com.contentsquare.autocomplete.App")

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)
