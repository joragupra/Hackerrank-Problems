object Solution {

    def main(args: Array[String]) {
        val msg = readLine
        print(compress(msg toList, 1) mkString)
    }
    
    def compress(msg: List[Char], rep:Int): List[Char] = msg match {
        case x::y::xs => if (x == y) compress(y::xs, rep + 1) else { if (rep > 1) x::rep.toString.toList:::compress(y::xs, 1) else x::compress(y::xs, 1) }
        case x::_ => if (rep > 1) x::rep.toString.toList:::compress(List(), 1) else x::compress(List(), 1)
        case List() => List()
    }

}
