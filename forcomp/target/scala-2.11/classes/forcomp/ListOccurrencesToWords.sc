def listX = List(3,2,1)
def listY = List(1,5)
def listZ = List(10, 20)
def list = List(listX, listY, listZ)
listZ

listX zip listY

def cartesianProduct(l1: List[Int], l2: List[Int]): List[List[Int]] = {
  for {
    x <- l1
    y <- l2
  } yield {
    x::y::Nil
  }
}
val listC = cartesianProduct(listX, listY)
//list.reduce
for {
  l <- listC
  //z <- listZ
} yield {
  println(l)
  cartesianProduct(l, listZ)
}

def prod(list: List[List[String]], acc : List[List[String]]) : List[List[String]] = {
  if (list.isEmpty)
    acc
  else {
    val newAcc = for {
      l <- acc
      x <- list.head
    } yield {
      println("Acc : " + l + "element: " + x)
      l ::: List(x)
    }
    prod(list.tail, newAcc)
  }
}

def cartessian(list : List[List[String]]) : List[List[String]] = {
  println("init cart: " + list)
  if (list.isEmpty) List(List())
  else prod(list, List(List()))
}
//list
//prod(List(listZ), List(listX, listY))
//cartessian(list)
cartessian(List(List("ate", "eat"), List("jamon", "monja")))

val listOfWords = List(List(List("all", "lla"), List("ate", "eat", "tea")), List(List("ate", "eat", "tea"), List("all", "lla")))
listOfWords
listOfWords.flatMap(cartessian(_))

val emptList = List(List())
val l = List(List("a", "b"))
l ::: emptList