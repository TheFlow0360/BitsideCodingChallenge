package de.bitside.discount

import de.bitside.product.ProductId

class DiscountRepository {

    // similar to the ProductRepository, we just keep a list of known discounts here
    // usually that would either be a database or a different service providing the data
    private val activeDiscounts = mutableMapOf<ProductId, DiscountType>()

    fun setDiscountForProduct(productId: ProductId, discountType: DiscountType) {
        activeDiscounts[productId] = discountType
    }

    fun resetDiscountForProduct(productId: ProductId) = setDiscountForProduct(productId, DiscountType.NONE)

    fun getDiscountForProduct(productId: String) = activeDiscounts[productId] ?: DiscountType.NONE
}