name := """morphia-play-injector"""

organization := "com.lvxingpai"

version := "0.1.3-SNAPSHOT"

crossScalaVersions := "2.10.4" :: "2.11.4" :: Nil

val morphiaVersion = "1.0.1"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  cache,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.lvxingpai" %% "morphia-factory" % "0.2.0",
  "org.mongodb.morphia" % "morphia" % morphiaVersion,
  "org.mongodb.morphia" % "morphia-validation" % morphiaVersion
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

