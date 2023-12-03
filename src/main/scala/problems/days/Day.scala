package problems.days

import java.io.{BufferedReader, FileReader}
import scala.util.Using

abstract class Day(inputPath: String) {
  protected lazy val input: Seq[String] = loadInput(inputPath)

  private def loadInput(inputPath: String): Seq[String] = {
    Using.resource(new BufferedReader(new FileReader(inputPath))) { reader =>
      Iterator.continually(reader.readLine()).takeWhile(_ != null).toSeq
    }
  }

  def solve(): Unit = {
    println(s"Part 1 = ${solveFirstPart()}\n" +
            s"Part 2 = ${solveSecondPart()}")
  }

  def solveFirstPart(): String

  def solveSecondPart(): String
}
