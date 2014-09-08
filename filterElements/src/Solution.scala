object Solution {

    class Repetition(val number:Int, val reps:Int) {

        def addRepetition(): Repetition = new Repetition(number, reps+1)

        override def toString():String = "[number: " + number + ", reps: " + reps + "]"

    }

    def main(args: Array[String]) {
        val T = readInt
        val results = for (i <- 1 to T) yield {
            val params = readLine.split(" ")
            val K = params(1) toInt
            val input = readLine.split(" ").map( _.toInt ).toList

            val repetitions = countRepetitions(input)
            removeDuplicatesAfterFirstAppearanceInList(filterNotRepeatedAtLeast(input, repetitions, K), List())            
        }
        for (r <- results) { println(r match { case List() => "-1" case nonEmpty => nonEmpty mkString " "}) }
    }

    def removeDuplicatesAfterFirstAppearanceInList(l:List[Int], exclude:List[Int]):List[Int] = l match {
        case List() => List()
        case x::xs => if (exclude contains x) removeDuplicatesAfterFirstAppearanceInList(xs, exclude) else x::removeDuplicatesAfterFirstAppearanceInList(xs, x::exclude)
    }

    def filterNotRepeatedAtLeast(numbers:List[Int], repetitions:List[Repetition], threshold:Int):List[Int] = numbers.filter( n => {
        repetitions.find( x => x.number == n ).exists( r => r.reps >= threshold )
        
    } )

    def countRepetitions(numbers:List[Int]):List[Repetition] = numbers match {
        case List() => List()
        case x::xs => {
            val repetitions = countRepetitions(xs)
            val pos = repetitions.indexWhere( n => n.number == x)
            if (pos != -1) {
                val old = repetitions(pos)
                repetitions.updated(pos, old.addRepetition)
            } else {
                new Repetition(x, 1)::repetitions
            }
        }
    }

}
