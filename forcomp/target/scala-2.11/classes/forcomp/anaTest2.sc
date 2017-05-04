type Word = String
type Sentence = List[Word]
type Occurrences = List[(Char, Int)]
val dictionary: List[Word] = List("ate", "eat", "tea", "all", "lla", "a")
//val dictionary: List[Word] = List("a", "vaca", "vac")
//val dictionary: List[Word] = List("a", "vaca", "vac", "va")
def wordOccurrences(w: Word): Occurrences = {
  val m = w.groupBy(e => e.toLower).toList
  m.map(x => (x._1, x._2.length)).sortWith(_._1 < _._1)
}
val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
  print(dictionary.groupBy(e => wordOccurrences(e)))
  dictionary.groupBy(e => wordOccurrences(e))
}
def subtract(x: Occurrences, y: Occurrences): Occurrences = {
  (for {
    xi <- x
  } yield {
    val yi = y.filter(_._1 == xi._1)
    if (yi.isEmpty) xi
    else (xi._1, xi._2 - yi.head._2)
  }).filter(_._2 != 0)
}
def combinations(occurrences: Occurrences): List[Occurrences] = {
  if (occurrences.isEmpty) List(List())
  else {
    val tailList = combinations(occurrences.tail)
    tailList ::: (for {
      o <- tailList
      i <- 1 to occurrences.head._2
    } yield {
      //println("item for: " + (occurrences.head._1, i))
      (occurrences.head._1, i) :: o
    })
  }
}
def getAnagramsOccurrences(occur : Occurrences): List[List[Occurrences]] = {
  val comb = combinations(occur)
  println("\nList: " + occur)
  print("Comb: ")
  comb.foreach(i => print("|" + i + "|"))
  val finalList = for {
    c <- comb
    if dictionaryByOccurrences.contains(c)
  } yield {
    println("\n\tDic: " + c)
    if (subtract(occur, c).isEmpty){
      println("\t \t selec: " + c)
      List(List(c))
    }
    else {
      //println("Next bucle:\n")
      val anagr = getAnagramsOccurrences(subtract(occur, c))
      //println("Fin bucle:\n")
      if (anagr.nonEmpty) {
        getAnagramsOccurrences(subtract(occur, c)).filter(_.nonEmpty).map(c::_)
      }
      else List()
    }
  }
  print("\tFIN: ")
  finalList.foreach(i => print(" || " + i))
  println("\n" + finalList)
  println("")
  //List()
  //  finalList.filter(_.isNotEmpty)   //.fold(List(List())(_:::_)
  finalList.foldLeft(List(): List[List[Occurrences]])(
    (B:List[List[Occurrences]], A:List[List[Occurrences]]) => B:::A)
}
val frase="avaca"
dictionary
val f = wordOccurrences(frase)
val anagrams = getAnagramsOccurrences(f)
anagrams
anagrams.map(_.map(dictionaryByOccurrences(_)))

val f2 = wordOccurrences("eatall")
val an2 = getAnagramsOccurrences(f2)
an2
an2.map(_.map(dictionaryByOccurrences(_)))
