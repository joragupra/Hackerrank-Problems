package similarity

object similarity {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(71); 
  val a = List('y','a','d','c');System.out.println("""a  : List[Char] = """ + $show(a ));$skip(28); 
  val b = List('x','a','b');System.out.println("""b  : List[Char] = """ + $show(b ));$skip(43); val res$0 = 
  (a zip b) indexWhere ((x) => x._1!=x._2);System.out.println("""res0: Int = """ + $show(res$0));$skip(272); 
  
  def similarity(a: List[Char], b: List[Char]): Int = {
    val firstDistinctFound = (a zip b) indexWhere ((x) => x._1!=x._2)
    println(a + " - " + b + " primero encontrado " + firstDistinctFound)
    if (firstDistinctFound > -1) firstDistinctFound else b.length
  };System.out.println("""similarity: (a: List[Char], b: List[Char])Int""");$skip(194); 
  def similaritiesWithSuffixes(a: List[Char]): Int = {
    val suffixes = for ( i <- (a.length-1) to 0 by -1 ) yield a.slice(i, a.length)
    (for(s <- suffixes) yield similarity(a, s)).sum
  };System.out.println("""similaritiesWithSuffixes: (a: List[Char])Int""");$skip(25); 
  
  
  val s = "ababaa";System.out.println("""s  : String = """ + $show(s ));$skip(37); val res$1 = 
  similaritiesWithSuffixes(s.toList);System.out.println("""res1: Int = """ + $show(res$1))}

}
