package greeter

object generalWorkshit {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val testint = 2+2                               //> testint  : Int = 4
  
  val x = 1                                       //> x  : Int = 1
  def increase(i: Int) = i + 1                    //> increase: (i: Int)Int
  increase(x)                                     //> res0: Int = 2
  
  val y = List(5,2,1)                             //> y  : List[Int] = List(5, 2, 1)
  
}