object Solution {

    def main(args: Array[String]) {
        val T = readInt

        val results = for (i <- 1 to T) yield permuteEven(readLine toList, List())

        print(results mkString "\n")
    }

    def permuteEven(s:List[Char], carry:List[Char]):String = s match {
        case List() => carry.reverse mkString
        case x::y::xs => permuteEven(xs, x::y::carry)
        case x::rest => permuteEven(rest, x::carry)
    }

}
