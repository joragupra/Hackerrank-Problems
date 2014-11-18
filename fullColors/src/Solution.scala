object Solution {

    def main(args: Array[String]) {
        val N = readLine.toInt
        val results = for (i <- 1 until N+1) yield fullColors(readLine.toList, 0, 0, 0, 0)
        results.foreach{x => println(x.toString.capitalize)}
    }
    
    def fullColors(colors: List[Char], red: Int, green: Int, blue: Int, yellow: Int): Boolean = colors match {
        case List() => (red == green) && (yellow == blue)
        case c::cx => {
            var colorMatrix: Array[Int] = Array(red, green, blue, yellow)
            colorMatrix(colorIndex(c)) = colorMatrix(colorIndex(c))+1
            checkPrefix(colorMatrix(0), colorMatrix(1), colorMatrix(2), colorMatrix(3)) && fullColors(cx, colorMatrix(0), colorMatrix(1), colorMatrix(2), colorMatrix(3))
        }
    }
    
    def colorIndex(c: Char): Int = if (c=='R') 0 else if (c=='G') 1 else if (c=='B') 2 else if (c=='Y') 3 else -1
    
    def checkPrefix(red: Int, green: Int, blue: Int, yellow: Int): Boolean = ((red-green)*(red-green) < 2) && ((blue-yellow)*(blue-yellow) < 2)

}
