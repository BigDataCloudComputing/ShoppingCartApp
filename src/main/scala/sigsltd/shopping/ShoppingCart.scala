/**
  * Created by Shobha on 2/22/2016.
  */
package sigsltd.shopping

sealed trait Fruit{ val price : BigDecimal}

case class ShoppingCart(Fruits: Fruit*)


case object Apple extends Fruit {
  val price = BigDecimal(0.60)
}

case object Orange extends Fruit {
  val price = BigDecimal(0.25)
}




