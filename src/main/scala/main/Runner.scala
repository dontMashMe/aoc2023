package main

import problems.days._

object Runner {
  def main(args: Array[String]): Unit = {
    val dayToRun = 2 //change this
    val formattedInput = s"input\\day$dayToRun.txt"

    val days: Vector[Day] = Vector(
      new DayOne(formattedInput),
      new DayTwo(formattedInput),
      // etc..
    )

    println(days(dayToRun - 1).solve())
  }
}
