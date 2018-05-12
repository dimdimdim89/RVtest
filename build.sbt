organization in ThisBuild := "dmitry.test"
version in ThisBuild := "0.1.0-SNAPSHOT"
name := "RvTest"

lazy val util = (project in file("util")).settings(name := "util", version := "0.0.1")
lazy val core = (project in file("core")).
  settings(name := "core", version := "0.0.1",
    libraryDependencies ++= Seq(guice, "com.h2database" % "h2" % "1.4.192", javaJdbc, evolutions, jdbc, "io.ebean" % "ebean" % "11.15.4"),
    playEbeanModels in Compile := Seq("test.db.entity.*")
  ).enablePlugins(PlayEbean).dependsOn(util)

lazy val root = (project in file(".")).
  settings(
    scalaVersion := "2.12.4"
  ).enablePlugins(PlayJava).dependsOn(util, core)

