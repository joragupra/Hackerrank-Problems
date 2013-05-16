package similarity

object Solution {
  
  val T = readInt
  val strings = for (i <- 1 to T) yield readLine
  
  def main(args: Array[String]) {
    for (s <- strings) println(similaritiesWithSuffixes(s toList))
  }
  
  def similaritiesWithSuffixes(a: List[Char]): Int = {
    val suffixes = for ( i <- (a.length-1) to 0 by -1 ) yield a.slice(i, a.length)  
    (for(s <- suffixes) yield similarity(a, s)).sum
  }  
  
  def similarity(a: List[Char], b: List[Char]): Int = {
    val firstDistinctFound = (a zip b) indexWhere ((x) => x._1!=x._2)
    if (firstDistinctFound > -1) firstDistinctFound else b.length
  }
}