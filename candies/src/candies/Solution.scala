package candies

object Solution {
  
  type Child = Int
  type Candies = Vector[Int]
  
  val N = readInt
  val ranks = (for (i <-1 to N) yield { readInt }).toVector

  def main(args: Array[String]) {
    val initialSolution = for (i <- 1 to N) yield 1
    val solution = solve(initialSolution.toVector)
    println(solution.sum)
  }
  
  def solve(candies: Vector[Int]): Vector[Int] = {
    var finished = true
    val newCandies = (for (i<-0 to N-1) yield {
      if (i == 0) {
        if (ranks(0) > ranks(1) && candies(0) <= candies(1)) { finished = false; candies(1)+1}
        else candies(0)
      }
      else if (i == N - 1) {
        if(ranks(N - 1) > ranks(N - 2) && candies(N - 1) <= candies(N - 2)) { finished = false; candies(N-2) + 1}
        else candies(N - 1)
      }
      else {
        if (ranks(i) > ranks(i - 1) && candies(i) <= candies(i - 1)) { finished = false; candies(i-1) + 1 }
        else if (ranks(i) > ranks(i + 1) && candies(i) <= candies(i + 1)) { finished = false; candies(i + 1) + 1 }
        else candies(i)
      }
    }).toVector
    if (finished) newCandies
    else solve(newCandies)
  }
}