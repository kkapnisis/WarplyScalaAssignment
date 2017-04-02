name := "CassScala"

val commonSettings = Seq(
  organization := "net.example",
  version := "0.1",
  scalaVersion := "2.11.4",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val testDependencies = Seq (
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

lazy val cassandraDependencies = Seq (
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.2",
  "com.chrisomeara" % "pillar_2.11" % "2.0.1"
)

lazy val playDependencies = Seq (
  jdbc,
  anorm,
  cache,
  ws
)

lazy val common = project.in(file("common"))
  .settings(commonSettings:_*)
  .settings(libraryDependencies ++= (testDependencies ++ cassandraDependencies))

lazy val playApp = project.in(file("playApp"))
  .settings(commonSettings:_*)
  .settings(libraryDependencies ++= (testDependencies ++ cassandraDependencies ++ playDependencies))
  .enablePlugins(PlayScala)
  .dependsOn(common)

lazy val main = project.in(file("."))
  .aggregate(common, playApp)