package de.bitside

import de.bitside.basket.BasketFactory
import de.bitside.discount.DiscountRepository
import de.bitside.discount.DiscountType
import de.bitside.product.ProductRepository

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // this would normally be handled on application initialization through dependency ínjection, e.g. spring beans
    val discountRepository = DiscountRepository()
    val basketFactory = BasketFactory(ProductRepository(), discountRepository)

    /* Task 0 */
    // create and fill basket
    val user0Basket = basketFactory.createBasketForUser("u0")
    user0Basket.scan("A0001")

    // output contents and total
    println("Basket Contents for Task 0: $user0Basket")
    println("Total: ${user0Basket.total()}")

    /* Task 1 */
    // set discount
    discountRepository.setDiscountForProduct("A0002", DiscountType.BUY_ONE_GET_ONE_FREE)
    val user1Basket = basketFactory.createBasketForUser("u1")
    user1Basket.scan("A0002")
    user1Basket.scan("A0001")
    user1Basket.scan("A0002")

    // output contents and total
    println("Basket Contents for Task 1: $user1Basket")
    println("Total: ${user1Basket.total()}")

    /* Task 2 */
    // reset previous discount
    discountRepository.resetDiscountForProduct("A0002")

    // set new, different discount
    discountRepository.setDiscountForProduct("A0001", DiscountType.TEN_PERCENT_OFF)

    // create and fill basket
    val user2Basket = basketFactory.createBasketForUser("u2")
    user2Basket.scan("A0002")
    user2Basket.scan("A0001")
    user2Basket.scan("A0002")

    // output contents and total
    println("Basket Contents for Task 2: $user2Basket")
    println("Total: ${user2Basket.total()}")
}