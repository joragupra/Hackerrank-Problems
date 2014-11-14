object Solution {

    def main(args: Array[String]) {
        val x = readLine toList
        val y = readLine toList
        
        val p = prefix(x, y)
        
        println(printable(p))
        println(printable(x drop p.size))
        println(printable(y drop p.size))
    }
    
    def prefix(x: List[Char], y: List[Char]): List[Char] = x match {
        case List() => List()
        case x1::xs => y match {
            case List() => List()
            case y1::ys => if (x1 == y1) x1::prefix(xs, ys) else List()
        }
    }
    
    def printable(s: List[Char]): String = s match {
        case List() => "0"
        case _ => s.size + " " + s.mkString
    }
    
}
