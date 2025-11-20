package de.bitside.basket

import de.bitside.discount.DiscountRepository
import de.bitside.product.Price
import de.bitside.product.ProductId
import de.bitside.product.ProductRepository

class Basket(
    private val productRepository: ProductRepository,
    private val discountRepository: DiscountRepository,
    private val userId: String,
    private val items: MutableMap<ProductId, BasketPosition> = mutableMapOf()
) {
    // this function only allows to add items to the basket through the use of the unsigned integer,
    // since cancelling ("Storno") oftentimes requires additional logic in a setting like this
    fun scan(productId: ProductId, amount: UInt = 1u) {
        if (amount == 0u) return
        val product = productRepository.getProductById(productId)
        // create a new basket position with the sum new and existing amounts, if already exist
        items.compute(productId) { _, position -> BasketPosition(product, amount + (position?.amount ?: 0u)) }
    }

    // sum up all the individual position prices (they already include their discounts)
    // this would also be the point to add total basket discount if necessary later on
    fun total(): Price = items.values.fold(Price(0)) { sum, position -> sum + position.calculatePrice(discountRepository) }

    override fun toString(): String {
        return "Basket for user $userId${items.values.fold("") { item, sum -> "$item\n$sum" }}\n"
    }
}
