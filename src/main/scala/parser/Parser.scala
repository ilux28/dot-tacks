package parser

import scala.io.Source.fromFile

class Parser(existHeader: Boolean = true) {
//  def readData(fileName:String): Vector[Vector[String]] = {
//    for {
//      lines <- fromFile(fileName).getLines().toVector
////      line <- if (existHeader) fromFile(fileName).getLines().toVector
//      data <- parseCsvLine(line)
//    } yield data
//  }
//
//  def parseCsvLine(line: String): Option[Vector[String]] = {
//    line.split(",").toVector.map(_.trim) match {
//      case stringVector => Some(stringVector)
//      case _ => println(s"WARNING UNKNOWN DATA FORMAT FOR LINE: $line")
//        None
//    }
//
//  }
}

object Parser {
//  def main(args: Array[String]): Unit = {
//    val file = "C:\\Users\\ilyap\\resource\\example_csv.csv"
//    val parser = new Parser
//    val result: Vector[Vector[String]] = parser.readData(file)
//    result.foreach(line => println(line.mkString(", ")))
//  }
}