package stockMaximize

object Solution {
  
  val T = readInt
  
  def main(args: Array[String]) {
    for (i <- 1 to T) {
      val N = readInt
      val stockValues: List[Long] = readLine.split(" ") map (_.toLong) toList
      
      println(maximize(stockValues.reverse, 0, 0))
    }
  }
  
  def maximize(stockValues: List[Long], profit: Long, maximum: Long): Long = stockValues match {
    case List() => profit
    case x::xs => if (x>=maximum) maximize(xs, profit, x) else maximize(xs, profit + maximum - x, maximum)
  }

}