package forcomp
import java.lang.Character.Subset

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
    * how often the character appears.
    * This list is sorted alphabetically w.r.t. to the character in each pair.
    * All characters in the occurrence list are lowercase.
    *
    * Any list of pairs of lowercase characters and their frequency which is not sorted
    * is **not** an occurrence list.
    *
    * Note: If the frequency of some character is zero, then that character should not be
    * in the list.
    */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
    * It is predefined and obtained as a sequence using the utility method `loadDictionary`.
    */
  val dictionary: List[Word] = loadDictionary

  /** Converts the word into its character occurrence list.
    *
    * Note: the uppercase and lowercase version of the character are treated as the
    * same character, and are represented as a lowercase character in the occurrence list.
    *
    * Note: you must use `groupBy` to implement this method!
    */
  def wordOccurrences(w: Word): Occurrences = {
    /*def calculateOccurences (occurrences: List[(Char, Int)], word: List[Char]) : List[(Char, Int)] = {
      word match {
        case Nil =>
          return occurrences
        case c :: ctail =>
          if (occurrences.exists(o => (o._1 == c))) {
            val newList = occurrences.map(x => if (x._1 == c) (x._1, x._2 + 1) else x)
            return calculateOccurences(newList, ctail)
          }
          else
            return calculateOccurences((c, 1) :: occurrences, ctail)
        case _ =>
          print("Error : extrange list founded")
          return Nil
      }
    }
    val occurrences = calculateOccurences(List[(Char, Int)](), w.toList)
    occurrences.sortWith(_._2 > _._2)*/
    val m = w.groupBy(e => e.toLower).toList
    m.map(x => (x._1, x._2.length)).sortWith(_._1 < _._1)
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {
    wordOccurrences(s.mkString)
  }

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
    * the words that have that occurrence count.
    * This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
    *
    * For example, the word "eat" has the following character occurrence list:
    *
    * `List(('a', 1), ('e', 1), ('t', 1))`
    *
    * Incidentally, so do the words "ate" and "tea".
    *
    * This means that the `dictionaryByOccurrences` map will contain an entry:
    *
    * List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
    *
    */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    //print(dictionary.groupBy(e => wordOccurrences(e)))
    dictionary.groupBy(e => wordOccurrences(e))
  }

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = {
    dictionaryByOccurrences.getOrElse(wordOccurrences(word), List())
  }

  /** Returns the list of all subsets of the occurrence list.
    * This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
    * is a subset of `List(('k', 1), ('o', 1))`.
    * It also include the empty subset `List()`.
    *
    * Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
    *
    * List(
    * List(),
    * List(('a', 1)),
    * List(('a', 2)),
    * List(('b', 1)),
    * List(('a', 1), ('b', 1)),
    * List(('a', 2), ('b', 1)),
    * List(('b', 2)),
    * List(('a', 1), ('b', 2)),
    * List(('a', 2), ('b', 2))
    * )
    *
    * Note that the order of the occurrence list subsets does not matter -- the subsets
    * in the example above could have been displayed in some other order.
    */
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
  } //???

  /** Subtracts occurrence list `y` from occurrence list `x`.
    *
    * The precondition is that the occurrence list `y` is a subset of
    * the occurrence list `x` -- any character appearing in `y` must
    * appear in `x`, and its frequency in `y` must be smaller or equal
    * than its frequency in `x`.
    *
    * Note: the resulting value is an occurrence - meaning it is sorted
    * and has no zero-entries.
    */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    (for {
      xi <- x
    } yield {
      val yi = y.filter(_._1 == xi._1)
      if (yi.isEmpty) xi
      else (xi._1, xi._2 - yi.head._2)
    }).filter(_._2 != 0)
  } //???

  /** Returns a list of all anagram sentences of the given sentence.
    *
    * An anagram of a sentence is formed by taking the occurrences of all the characters of
    * all the words in the sentence, and producing all possible combinations of words with those characters,
    * such that the words have to be from the dictionary.
    *
    * The number of words in the sentence and its anagrams does not have to correspond.
    * For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
    *
    * Also, two sentences with the same words but in a different order are considered two different anagrams.
    * For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
    * `List("I", "love", "you")`.
    *
    * Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
    *
    * List(
    * List(en, as, my),
    * List(en, my, as),
    * List(man, yes),
    * List(men, say),
    * List(as, en, my),
    * List(as, my, en),
    * List(sane, my),
    * List(Sean, my),
    * List(my, en, as),
    * List(my, as, en),
    * List(my, sane),
    * List(my, Sean),
    * List(say, men),
    * List(yes, man)
    * )
    *
    * The different sentences do not have to be output in the order shown above - any order is fine as long as
    * all the anagrams are there. Every returned word has to exist in the dictionary.
    *
    * Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
    * so it has to be returned in this list.
    *
    * Note: There is only one anagram of an empty sentence.
    */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def getAnagramsOccurrences(occur : Occurrences): List[List[Occurrences]] = {
      val comb = combinations(occur)
      //println("\nList: " + occur)
      //print("Comb: ")
      //comb.foreach(i => print("|" + i + "|"))
      val finalList = for {
        c <- comb
        if dictionaryByOccurrences.contains(c)
      } yield {
        //println("\n\tDic: " + c)
        if (subtract(occur, c).isEmpty){
          //println("\t \t selec: " + c)
          List(List(c))
        }
        else {
          //println("Next bucle:\n")
          val anagrams = getAnagramsOccurrences(subtract(occur, c))
          //println("Fin bucle:\n")
          if (anagrams.nonEmpty) {
            //getAnagramsOccurrences(subtract(occur, c)).filter(_.nonEmpty).map(c::_)
            anagrams.filter(_.nonEmpty).map(c::_)
          }
          else List()
        }
      }
      //print("\tFIN: ")
      //finalList.foreach(i => print(" || " + i))
      //println("\n" + finalList)
      //println("")
      //List()
      //  finalList.filter(_.isNotEmpty)   //.fold(List(List())(_:::_)
      finalList.foldLeft(List(): List[List[Occurrences]])(
        (B:List[List[Occurrences]], A:List[List[Occurrences]]) => B:::A)
    }

    def occurrencesToSentences(list: List[List[Word]], acc : List[Sentence]) : List[Sentence] = {
      if (list.isEmpty)
        acc
      else {
        val newAcc = for {
          l <- acc
          x <- list.head
        } yield {
          //println("Acc : " + l + "element: " + x)
          l ::: List(x)
        }
        occurrencesToSentences(list.tail, newAcc)
      }
    }

    if (sentence.isEmpty) List(Nil)
    else {
      val anagramsOccurrences = getAnagramsOccurrences(sentenceOccurrences(sentence)) // List[List[Occurrr]]
      if (anagramsOccurrences.isEmpty) List()
      else {
        val listAnagrams: List[List[List[Word]]] = anagramsOccurrences.map(_.map(dictionaryByOccurrences(_))) //
        listAnagrams.flatMap(occurrencesToSentences(_, List(List())))
      }
    }
  }
}