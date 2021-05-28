import Dependencies.{streams, akka, cats, circe, http4s, jackson, slf4j, tests}

name := "dot-tacks"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(cats, http4s, jackson, circe, tests, slf4j, akka, streams).flatten