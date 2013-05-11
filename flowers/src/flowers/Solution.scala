package flowers

object Solution {
  
  val params = readLine().split(" ").toList map(_.toInt)
  val N = params head
  val K = params.tail head
  val flowers = readLine().split(" ").toList map(_.toInt)
  
  def main(args: Array[String]) {
    val friends = for (i <- 1 to K) yield 0
    println(buyFlowers(friends toList, flowers, 0))
  }
  
  def buyFlowers(friends: List[Int], flowers: List[Int], spent: Int): Int =
    if (allFlowersWereBought(flowers)) spent
    else {
      val (currentFlowers, nextFriend) = friends.zipWithIndex.min
      val (flowerCost, mostExpensiveFlower) = flowers.zipWithIndex.max
      buyFlowers(
          friends.updated(nextFriend, currentFlowers + 1),
          flowers.updated(mostExpensiveFlower, 0),
          spent + (currentFlowers + 1)*flowerCost)
    }
  
  def allFlowersWereBought(flowerCosts: List[Int]): Boolean =
    flowerCosts forall(_==0)
}