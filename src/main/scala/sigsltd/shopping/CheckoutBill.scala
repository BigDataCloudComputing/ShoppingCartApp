package sigsltd.shopping

import sigsltd.shopping.CheckoutBill.Discount

trait CheckOut{
  def Price(shoppingCart: ShoppingCart): BigDecimal
  def CheckOutPrice(shoppingCart: ShoppingCart, discounts: (ShoppingCart=>Discount)*): BigDecimal
}

object CheckoutBill extends CheckOut{
  type Discount = BigDecimal

  //Price without discount
  def Price(shoppingCart: ShoppingCart): BigDecimal = {
    if(shoppingCart == null) throw new IllegalArgumentException("shopping cart is not valid")
    if(shoppingCart.Fruits.isEmpty) throw new Exception("shopping cart is empty")
    shoppingCart.Fruits.view.map(_.price).sum
  }

  //Final checkout price
  def CheckOutPrice(shoppingCart: ShoppingCart, discounts: (ShoppingCart => Discount)*): BigDecimal = {

    if (shoppingCart == null) throw new IllegalArgumentException("shopping cart is not valid")
    if (shoppingCart.Fruits.length == 0) throw new Exception("shopping cart is empty")

    //No discounts provided
    if (discounts == null)
      Price(shoppingCart)
    else {
      //Discounts provided
      val discount = discounts.foldLeft(BigDecimal(0.00)) {
        (totaldiscount, discount) => totaldiscount + discount(shoppingCart)
      }
      shoppingCart.Fruits.map(_.price).sum - discount
    }

  }

}
