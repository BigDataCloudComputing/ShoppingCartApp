package sigsltd.shopping

import org.scalatest.{BeforeAndAfter,  FunSuite}

/**
  * Created by Shobha on 2/22/2016.
  */
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
    assert(CheckoutBill.Price(shoppingCart.get) == 1.95)
  }

  test("When the cart is null then the CheckOutPrice for CheckOutBill throws IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      CheckoutBill.CheckOutPrice(null)
    }
  }
  test("When the cart is empty then the CheckOutPrice for CheckOutBill throws an Exception") {
    intercept[Exception] {
      CheckoutBill.CheckOutPrice(ShoppingCart())
    }
  }
  test("When the cart contains 2 Apples and 3 Oranges and no discount then the CheckOutPrice for CheckOutBill returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(shoppingCart.get, DiscountCalculator.noDiscount) == 1.95)
  }

  test("When the cart contains 2 Apples and 3 Oranges and no discount then the CheckOutPrice for appleDiscount returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(shoppingCart.get, DiscountCalculator.appleDiscount) == 1.35)
  }

  test("When the cart contains 2 Apples and 3 Oranges and no discount then the CheckOutPrice for orangeDiscount returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(shoppingCart.get, DiscountCalculator.orangeDiscount) == 1.45)
  }
  test("When the cart contains 2 Apples and 3 Oranges and no discount then the CheckOutPrice for appleDiscount & orangeDiscount returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(shoppingCart.get, DiscountCalculator.appleDiscount, DiscountCalculator.orangeDiscount) == 0.85)
  }

  //No Discount will be ignored if discount is provided
  test("When the cart contains 2 Apples and 3 Oranges and no discount then the CheckOutPrice for noDiscount, appleDiscount & orangeDiscount returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(shoppingCart.get, DiscountCalculator.noDiscount,DiscountCalculator.appleDiscount, DiscountCalculator.orangeDiscount) == 0.85)
  }
  test("When the cart contains 5 Apples and 7 Oranges and no discount then the CheckOutPrice for appleDiscount & orangeDiscount returns a price without discount"){
    assert(CheckoutBill.CheckOutPrice(bigShoppingCart.get, DiscountCalculator.appleDiscount, DiscountCalculator.orangeDiscount) == 2.55)
  }

}
