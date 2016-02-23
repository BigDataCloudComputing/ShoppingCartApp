package sigsltd.shopping

trait CheckOut{
  def Price(shoppingCart: ShoppingCart): BigDecimal
}
object CheckoutBill extends CheckOut{

  //Price without discount
  def Price(shoppingCart: ShoppingCart): BigDecimal = {
    if(shoppingCart == null) throw new IllegalArgumentException("shopping cart is not valid")
    if(shoppingCart.Fruits.isEmpty) throw new Exception("shopping cart is empty")
    shoppingCart.Fruits.view.map(_.price).sum
  }
}
