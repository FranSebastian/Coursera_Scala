def numbers(n:Int) = (1 until n).toList

val n10 = numbers(10)

val nums = numbers(6):::numbers(3):::numbers(10)

val m = nums.groupBy(e=>e)

nums.groupBy(e=>e)

m(2).length

m.toList.map(x => (x._1, x._2.length)).sortWith(_._2 > _._2)

val str= "Memedep dep"
//val lower = str.toLowerCase.toList
val lower = str
val m2 = lower.groupBy(e=>e.toLower).toList //.flatMap((x,y) => (x, y.length))
m2.map(x => (x._1, x._2.length)).sortWith(_._2 > _._2)

val s="Una Grande y Libre toma ya con dos CojONZACOS"
val listOfWords = List("Una", "Grande", "Y", "Libre")
listOfWords.mkString
s.toList.filter(c => (c != ' ')).mkString
s.filter(_!=' ')

def wordOcc(w: String) = {
  val m = w.groupBy(e=>e.toLower).toList
  m.map(x => (x._1, x._2.length)) //.sortWith(_._2 > _._2)
}

wordOcc(s.filter(_!=' '))

val s2="HOLa"
val s1="TATA"
val l = List(s1, s2, "tu puta madre encebollada")
l.flatMap(s=>s).mkString
l.mkString

/**********************************/
val dict = List("ate", "eat", "tea", "Bastard", "cake", "jamon", "monja", "majon")
val dict2 = List("ScallaC", "caallsC", "ccslala")
wordOcc(dict.head)
wordOcc("jamon")
wordOcc("amnoj")
wordOcc("tae")
wordOcc("eat")
wordOcc("tea")

dict2.groupBy(e=>wordOcc(e))
nums.groupBy(e=>(e%2, (e+1)%2))
val dicByOcc = dict.groupBy(e=>wordOcc(e))
dicByOcc.getOrElse(wordOcc("jamon"), List())
dicByOcc getOrElse (wordOcc("jamon"), List())
val l1 = List(1,2,3)
l1.foldLeft("t"){(i, o) => println(i); i + o}

l1.foldRight("a")(_+_)
