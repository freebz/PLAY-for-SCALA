name := """ean"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "net.sf.barcode4j" % "barcode4j" % "2.1"
)

organization := "com.example"

publishTo := Some(
  "My resolver" at "https://mycompany.com/repo"
)

credentials += Credentials(
  "Repository Realm", "https://mycompny.com/repo", "username", "hashed-password"
)
