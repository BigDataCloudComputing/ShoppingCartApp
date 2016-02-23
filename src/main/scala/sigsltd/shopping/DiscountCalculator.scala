package sigsltd.shopping

/**
  * Created by Shobha on 2/23/2016.
  */
trait Discounts{
  type Discount = BigDecimal
}
object DiscountCalculator extends Discounts {

  val noDiscount:(ShoppingCart)=>Discount =
    shoppingCart => {0}

  val appleDiscount: (ShoppingCart) => Discount  =
    shoppingCart => {
      val apples = shoppingCart.Fruits collect { case apple @ Apple => apple }
      math.floor(apples.size / 2) * Apple.price
    }

  val orangeDiscount: ShoppingCart => Discount  =
    shoppingCart => {
      val oranges = shoppingCart.Fruits collect { case orange @ Orange => orange }
      (math.floor(oranges.size / 3)* 2) * Orange.price

    }
}

