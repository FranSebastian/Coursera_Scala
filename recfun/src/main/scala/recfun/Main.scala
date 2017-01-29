package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    
    println("test changes first commit")
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = ???
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = ???
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = { 
      
      def calcWithFirstCoin(value : Int, c : List[Int]) : Int = {
//		    println("value : " + value + " c : " + c)
        if (value <= 0 || c.isEmpty || value < c.head ) 0 // means not possible to return value using first coin of the list
		    else   
		      if ( value == c.head ) 1 // 1 means you can use this coin to return the exact value ## 
		      else calcWithFirstCoin(value - c.head, c) + countChange(value - c.head, c.tail)
      }      
//      println("money : "+ money + "; coins : " + coins)
    	if ( coins.isEmpty ) 0
    	else calcWithFirstCoin(money, coins) + countChange(money, coins.tail)
    }
  }
