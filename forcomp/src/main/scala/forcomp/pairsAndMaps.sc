object pairs {
  def pairs(n: Int) = (0 until n) map (i =>
    (0 until i) map (j => (i, j)))

  pairs(5)
  pairs(8)(3)
  (1 until 3) map (j => (3, j))

  pairs(5)(3)
  def flat = pairs(5).flatten
  flat(0)

  def fMap(n: Int) = (1 until n) flatMap
    (i=>(1 until i) map (j=> (i,j)))

  def pairs5 = fMap(5)
  pairs5
  pairs5.filter(p => (p._1 + p._2)<=5)

  def isPrime(n: Int) : Boolean = {
    (2 until n) forall (n%_ != 0)
  }
  (2 until 4)
  (2 until 10)
  Range(2, 10).filter(13%_ == 0).isEmpty

  def squares(n: Int) = (1 until n+1) map
    (i => (i, i*i))

  val sq5 = squares(5)
  sq5

  val lUns = sq5.map( x => if (x._1%2==0) (x._1,x._2/2) else x)

  lUns.sortWith(_._2 > _._2)
}
