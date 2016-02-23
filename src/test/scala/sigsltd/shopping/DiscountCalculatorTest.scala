package sigsltd.shopping


import org.scalatest.{BeforeAndAfter, FunSuite}
/**
  * Created by Shobha on 2/23/2016.
  */
class DiscountCalculatorTest extends FunSuite with BeforeAndAfter {

  var shoppingCart1:Option[ShoppingCart] = None
  var shoppingCart2:Option[ShoppingCart] = None
  var shoppingCart3:Option[ShoppingCart] = None
  var shoppingCart4:Option[ShoppingCart] = None

  before {
    shoppingCart1 = Some(ShoppingCart(Apple, Apple, Orange, Orange, Orange))
    shoppingCart2 = Some(ShoppingCart(Apple, Apple, Apple, Orange, Orange, Orange, Orange))
    shoppingCart3 = Some(ShoppingCart(Apple, Apple, Apple,Apple,
      Orange, Orange, Orange, Orange, Orange, Orange))

  }

  after {
    var shoppingCart:Option[ShoppingCart] = None
  }

  test("When shopping cart1  has 2 apples and 3 oranges Then apply apple discount to return the discounted price") {
    assert (DiscountCalculator.appleDiscount(shoppingCart1.get) equals 0.60)
  }

  test("When shopping cart1  has 3 apples and 3 oranges Then Then apply orange discount to return the discounted price") {
    assert (DiscountCalculator.orangeDiscount(shoppingCart1.get) equals 0.50)
  }


  test("When shopping cart2  has 3 apples and 4 oranges Then apply discount for apple to return the discounted price ") {
    assert (DiscountCalculator.appleDiscount(shoppingCart2.get) equals 0.60)
  }

  test("When shopping cart2  has 3 apples and 4 oranges Then apply discount for orange to return the discounted price ") {
    assert (DiscountCalculator.orangeDiscount(shoppingCart2.get) equals 0.50)
  }


  test("When shopping cart3  has 4 apples and 6 oranges Then apply discount for apple to return half price for apples ") {
    assert (DiscountCalculator.appleDiscount(shoppingCart3.get) equals 1.20)
  }

  test("When shopping cart3  has 4 apples and 6 oranges Then apply discount for orange to return half price for oranges ") {
    assert (DiscountCalculator.orangeDiscount(shoppingCart3.get) equals 1.00)
  }

  test("When shopping cart3  has 4 apples and 6 oranges Then no discount for fruits returns 0") {
    assert (DiscountCalculator.noDiscount(shoppingCart3.get) equals 0)
  }

}

