object Solution {
    
    import scala.collection.mutable
        
    private[this] val memoized = mutable.Map.empty[Long, Long]

    def main(args: Array[String]) {
        val N = readLine.toInt
        val results = for (i <- 1 until N+1) yield memo(readLine.toLong, fibo)
        results.foreach{x => println(x)}
    }
    
    def fibo(n: Long):Long = (if (n <= 1) n else memo(n - 2, fibo) + memo(n - 1, fibo)) % (100000007)
    
    def memo(n: Long, f:Long => Long):Long = memoized.getOrElseUpdate(n, f(n))
        
}