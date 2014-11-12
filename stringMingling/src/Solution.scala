object Solution {

    def main(args: Array[String]) {
        val P = readLine
        val Q = readLine
        
        print(mingle(P toList, Q toList) mkString)
    }
    
    def mingle(s1: List[Char], s2: List[Char]): List[Char] = s1 match {
        case List() => List()
        case x::xs => s2 match {
            case y::ys => x::y::mingle(xs, ys)
            case _ => List()
        }
    }

}
