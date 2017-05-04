val ocur = List(('a', 2), ('c', 1), ('b', 2))

for {
  o <- ocur
  if (o._2 - 3 > 0)
} yield {
  (o._1, o._2 -1)
}

