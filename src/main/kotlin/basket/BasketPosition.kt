package de.bitside.basket

import de.bitside.discount.DiscountRepository
import de.bitside.product.Price
import de.bitside.product.ProductEntity
import java.math.BigDecimal

data class BasketPosition(val product: ProductEntity, val amount: UInt) {
    // calculate the price of this position by multiplying product price and amount, subtracting potential discounts
    fun calculatePrice(discountRepository: DiscountRepository): Price {
        val discount = discountRepository.getDiscountForProduct(product.id).calculateDiscount(this.product.price, this.amount)
        return product.price * BigDecimal.valueOf(amount.toLong()) - discount
    }

    override fun toString(): String {
        return "${amount}x $product"
    }
}
