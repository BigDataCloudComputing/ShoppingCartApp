/**
  * Created by Shobha on 2/22/2016.
  */

package sigsltd.shopping

trait Generic{
  implicit def toOption[T](x:T) : Option[T] = Option(x)
}

trait CheckOut extends Generic {
  type Discount = BigDecimal
  def validateOrder(cart: ShoppingCart): (Boolean, Option[ShoppingCart]) = (cart.isInstanceOf[ShoppingCart], None)
  def Price(shoppingCart: ShoppingCart): BigDecimal
  def CheckOutPrice(shoppingCart: ShoppingCart, discounts: (ShoppingCart=>Discount)*): BigDecimal
}

object CheckoutBill extends CheckOut{
  //Price without discount
  def Price(shoppingCart: ShoppingCart): BigDecimal = {
    val (isOK, message) = validateOrder(shoppingCart)
    if (!isOK) throw new IllegalArgumentException("shopping cart is not valid")
    if(shoppingCart.Fruits.isEmpty) throw new Exception("shopping cart is empty")
    shoppingCart.Fruits.view.map(_.price).sum
  }
  //Final checkout price
  def CheckOutPrice(shoppingCart: ShoppingCart, discounts: (ShoppingCart => Discount)*): BigDecimal = {
    val (isOK, message) = validateOrder(shoppingCart)
    if (!isOK) throw new IllegalArgumentException("shopping cart is not valid")

    if(shoppingCart.Fruits.isEmpty) throw new Exception("shopping cart is empty")

    //No discounts provided
    if (discounts == null)
      Price(shoppingCart)
    else
    {
      //Discounts provided
      val discount = discounts.foldLeft(BigDecimal(0.00)) {
        (totaldiscount, discount) => totaldiscount + discount(shoppingCart)}
      shoppingCart.Fruits.map(_.price).sum - discount
    }

  }
}
