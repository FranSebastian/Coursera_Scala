package greeter

object generalWorkshit {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  println("Welcome to the Scala worksheet");$skip(23); 
  
  val testint = 2+2;System.out.println("""testint  : Int = """ + $show(testint ));$skip(15); 
  
  val x = 1;System.out.println("""x  : Int = """ + $show(x ));$skip(31); 
  def increase(i: Int) = i + 1;System.out.println("""increase: (i: Int)Int""");$skip(14); val res$0 = 
  increase(x);System.out.println("""res0: Int = """ + $show(res$0));$skip(25); 
  
  val y = List(5,2,1);System.out.println("""y  : List[Int] = """ + $show(y ))}
  
}
