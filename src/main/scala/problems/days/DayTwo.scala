package problems.days

class DayTwo(inputPath: String) extends Day(inputPath) {

  override def solveFirstPart(): String = {
    val rules: Map[String, Int] = Map(
      "red"   -> 12,
      "green" -> 13,
      "blue"  -> 14
    )
    val totalGameIdSum = input.map(line => "\\d+".r.findFirstIn(line).getOrElse("").toInt).sum

    val impossibleGamesIdSum: Int = input.foldLeft(0) { (cumulativeSum, line) =>
      val gameId = "\\d+".r.findFirstIn(line).getOrElse("")
      var sum = cumulativeSum

      line.split(":")(1).trim.split("; ").foreach {
        var seen = false
        set =>
          set.split(", ").foreach {
            setPieces =>
              val parts = setPieces.split("\\s+")
              if (!seen && parts(0).toInt > rules.getOrElse(parts.tail.mkString(" "), 0)) {
                sum += gameId.toInt
                seen = true
              }
          }
      }
      sum
    }

    s"${totalGameIdSum - impossibleGamesIdSum}"
  }

  override def solveSecondPart(): String = {
    val minimumSetOfCubes: List[Map[String, Int]] = input.map { line =>
      line.split(":")(1).trim.split("; ").foldLeft(Map[String, Int]()) {
        (maxValueMap, set) => {
          set.split(", ").foldLeft(maxValueMap) { (tempMap, setPieces) =>
            val parts = setPieces.split("\\s+")
            tempMap.updated(
              parts.tail.mkString(" "),
              math.max(
                tempMap.getOrElse(parts.tail.mkString(" "), 0),
                parts(0).toInt
              ))
          }
        }
      }
    }.toList

    minimumSetOfCubes.foldLeft(0) {
      (valuesPower, mapOfValues) =>
        valuesPower + mapOfValues.values.product
    }.toString
  }
}