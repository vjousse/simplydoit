package doit

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

case class FileDriver(filePath: String) extends TickDriver {

  val fstream = new FileWriter(filePath)
  val out = new BufferedWriter(fstream)
  out.write("0")
  out.close()

  //TODO: implement
  def tick(): Int = {

    val content = scala.io.Source.fromFile(filePath).mkString

    //Try to parse some int
    val intValue = getIntValue(content)

    val fstream = new FileWriter(filePath)
    val out = new BufferedWriter(fstream)
    out.write("%d".format(intValue.getOrElse(0) + 1))
    out.close()

    intValue.getOrElse(0) + 1
  }

  private def getIntValue(content: String): Option[Int] = {
    try {
      Some(content.toInt)
    } catch {
      case _: java.lang.NumberFormatException => None
    }
  }
}
