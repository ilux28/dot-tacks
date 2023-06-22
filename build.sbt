import Dependencies.{akka, cats, circe, http4s, jackson, scalaParallel, slf4j, streams, tests}

enablePlugins(ScalaJSPlugin)

name := "dot-tacks"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(cats, http4s, jackson, circe, tests, slf4j, akka, streams, scalaParallel).flatten

scalaJSUseMainModuleInitializer := true