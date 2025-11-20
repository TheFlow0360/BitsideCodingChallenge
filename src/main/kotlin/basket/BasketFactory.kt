package de.bitside.basket

import de.bitside.discount.DiscountRepository
import de.bitside.product.ProductRepository

// supporting class for dependency-injection style object creation
class BasketFactory(val productRepository: ProductRepository, val discountRepository: DiscountRepository) {
    fun createBasketForUser(userId: String) = Basket(productRepository, discountRepository, userId)
}