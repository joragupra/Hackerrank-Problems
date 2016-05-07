object Solution {
    
    import scala.collection.mutable
        
    private[this] val memoized = mutable.Map.empty[Long, Long]

    def main(args: Array[String]) {
        val N = readLine.toInt
        val results = for (i <- 1 until N+1) yield memo(readLine.toInt, pent)
        results.foreach{x => println(x)}
    }
    
    def pent(n: Long):Long = (n * (3 * n - 1) ) / 2
    
    def memo(n: Long, f:Long=>Long):Long = memoized.getOrElse(n, f(n))
    
    def pentagonal(n:Long):Long =
        if (n > 1) {
            val commonDots = 2 * (n - 1) - 1
            val newPetagon = if (n==1) 1 else 5*(n-1)
            newPetagon + memo(n - 1, pentagonal) - commonDots
        } else 1
        
}