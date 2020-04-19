name := "AutoComplete"

version := "0.1"
mainClass in Compile := Some("com.contentsquare.autocomplete.App")

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)

coverageEnabled := true
coverageOutputHTML := true