/**
  * Created by Shobha on 2/23/2016.
  */
package sigsltd.shopping

import org.scalatest.FunSuite


class ShoppingCartTest extends FunSuite {

  test("Using intercept to catch an Exception when cart is empty Then return Exception") {
    intercept[Exception] {
      ShoppingCart(null).Fruits.view.map(_.price).sum equals -1
    }
  }

  test("When add fruits in shopping cart when 2 Apples are provided Then cart contains 2 items and the correct price") {
    assert (ShoppingCart(Apple, Apple).Fruits.size equals 2)
    assert (ShoppingCart(Apple, Apple).Fruits.view.map(_.price).sum equals 1.20)

  }
  test("When add fruits in shopping cart when 3 Oranges are provided Then cart contains 3 items and the correct price") {
    assert (ShoppingCart(Orange, Orange, Orange).Fruits.size equals 3)
    assert (ShoppingCart(Orange, Orange, Orange).Fruits.map(_.price).sum equals 0.75)
  }

  test("When add Mix of Fruits (2 Apples and 2 Oranges) in shopping cart Then cart contains 5 items and the correct price") {
    assert (ShoppingCart(Apple, Apple, Orange, Orange, Orange).Fruits.size equals 5)
    assert (ShoppingCart(Apple, Apple, Orange, Orange, Orange).Fruits.view.map(_.price).sum equals 1.95)

  }

}

