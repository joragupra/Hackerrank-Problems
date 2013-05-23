package fraudPrevention

object Solution {

  val initialDataBase = Map[Int, Set[(Int, Customer)]]() withDefaultValue(Set())
  val N = readInt
  val orders = for (i <- 1 to N) yield {
    val orderData = readLine split(",")
    Order(orderData(0).toInt,orderData(1).toInt,orderData(2),orderData(3),orderData(4),orderData(5),orderData(6),orderData(7))
  }
  
  def main(args: Array[String]) {
    print(FraudDetector.detectFraud(orders.toList, initialDataBase, Set()).toList.sorted.mkString(","))
  }
  
  case class Address(a: String) {
    val canonicalForm = canonize(a)
    
    def canonize(address: String): String =
      address.toLowerCase.replaceAll("st\\.", "street").replaceAll("street\\.", "street").replaceAll("rd\\.", "road").replaceAll("road\\.", "road").replaceAll("\\s+", " ")
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: Address => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class Email(e: String) {
    val canonicalForm = canonize(e)
    
    def canonize(email: String): String = {
      val parts = email.toLowerCase split "@"
      val username = parts(0)
      val host = parts(1)
      username.replaceAll("\\.", "").replaceAll("\\+.+", "") + "@" + host
    }
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: Email => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class State(s: String) {
    val canonicalForm = canonize(s)
    
    def canonize(state: String): String = state.toLowerCase match {
      case "il" => "illinois"
      case "ca" => "california"
      case "ny" => "new york"
      case _ => state.toLowerCase.replaceAll("\\s+", " ")
    }
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: State => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class City(c: String) {
    val canonicalForm = canonize(c)
    
    def canonize(city: String): String = city.toLowerCase.replaceAll("\\s+", " ")
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: City => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class ZipCode(z: String) {
    val canonicalForm = canonize(z)
    
    def canonize(zip: String): String = zip toLowerCase
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: ZipCode => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class CreditCard(cc: String) {
    val canonicalForm = canonize(cc)
    
    def canonize(creditCard: String): String = creditCard toLowerCase
    
    override def toString(): String = canonicalForm
    
    override def equals(that: Any) = that match {
      case other: CreditCard => other.canonicalForm == this.canonicalForm
      case _ => false
    }
  }
  
  case class Customer(e: String, a: String, c: String, s: String, z: String, cc:String) {
    val email: Email = Email(e)
    val address: Address = Address(a)
    val city: City = City(c)
    val state: State = State(s)
    val zipCode: ZipCode = ZipCode(z)
    val creditCard: CreditCard = CreditCard(cc)
    
    override def toString(): String =
      "Customer [" + email.toString + "," + address.toString + "," + city.toString + "," + state.toString + "," + zipCode.toString + "," + creditCard.toString + "]"
      
    def isFraudulentVariation(other: Customer) =
      if (other.email.equals(this.email) && !other.creditCard.equals(this.creditCard)) true
      else if (other.address.equals(this.address) && other.city.equals(this.city) && other.state.equals(this.state) && other.zipCode.equals(this.zipCode) && !other.creditCard.equals(this.creditCard)) true
      else false
  }
  
  case class Order(orderId: Int, dealId: Int, e: String, a: String, c: String, s: String, z: String, cc:String) {
    val customer: Customer = Customer(e, a, c, s, z, cc)
  }
  
  object FraudDetector {
    def detectFraud(orders: List[Order], dataBase: Map[Int, Set[(Int, Customer)]], found: Set[Int]): Set[Int] = orders match {
      case List() => found
      case order::more => {
        val customersForDeal = dataBase(order.dealId)
        if (customersForDeal isEmpty) detectFraud(more, dataBase + (order.dealId -> Set((order.orderId, order.customer))), found)
        else {
          val fraudFound = customersForDeal find(_._2.isFraudulentVariation(order.customer))
          fraudFound match {
            case Some((firstOrderId, _)) => detectFraud(more, dataBase, found + order.orderId + firstOrderId)
            case None => detectFraud(more, dataBase + (order.dealId -> (customersForDeal + ((order.orderId, order.customer)))), found)
          }
        }
      }
    }
  }
}