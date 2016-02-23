package sigsltd.shopping

/**
  * Created by Shobha on 2/23/2016.
  */
import org.scalatest.{BeforeAndAfter, FunSuite}

class CheckoutBillTest extends FunSuite with BeforeAndAfter {

  var shoppingCart:Option[ShoppingCart] = None
  var bigShoppingCart:Option[ShoppingCart] = None


  before {
    shoppingCart = Some(ShoppingCart(Apple, Apple, Orange, Orange, Orange))
    bigShoppingCart = Some(ShoppingCart(Apple, Apple, Apple, Apple, Apple, Orange, Orange, Orange, Orange, Orange, Orange,Orange))
  }

  after {
    shoppingCart = None
    bigShoppingCart = None
  }

  test("When the cart is null then the price for CheckOutBill throws  IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      CheckoutBill.Price(null)
    }
  }

  test("When the cart is empty then the price for CheckOutBill throws an Exception") {
    intercept[Exception] {
      CheckoutBill.Price(ShoppingCart())
    }
  }

  test("When the cart has 2 Apples and 3 Oranges then Price for CheckOutBill is without discount") {
    assert(CheckoutBill.Price(shoppingCart.get) equals 1.95)
  }


}
