package problems.days

class DayOne(inputPath: String) extends Day(inputPath) {
  //add 1st and 2nd digit of each line
  //sum all the values
  override def solveFirstPart(): String = {
    input
      .map(line => line.collect { case char if char.isDigit => char.toString })
      .map(digits => digits.headOption.getOrElse("") + digits.lastOption.getOrElse(""))
      .map(_.toInt)
      .sum
      .toString
  }

  //some of the digits are spelled out
  override def solveSecondPart(): String = {
    val digitMap: Map[String, String] = Map(
      "one" -> "1",
      "two" -> "2",
      "three" -> "3",
      "four" -> "4",
      "five" -> "5",
      "six" -> "6",
      "seven" -> "7",
      "eight" -> "8",
      "nine" -> "9"
    )

    def replaceWords(line: String): String = {
      val keys = digitMap.keys.mkString("|") + "|"
      val regexPattern = s"(?=($keys\\d))".r

      regexPattern.replaceAllIn(line, matchResult => digitMap.getOrElse(matchResult.group(1), ""))
    }

    input
      .map(line => replaceWords(line))
      .map(line => { line.collect { case char if char.isDigit => char.toString } })
      .map(digits => digits.headOption.getOrElse("") + digits.lastOption.getOrElse(""))
      .map(_.toInt)
      .sum
      .toString
  }

}
