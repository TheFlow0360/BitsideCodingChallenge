package de.bitside.discount

import de.bitside.product.Price
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Type to describe possible discounts with their logic.
 * Limited to per position discounts, would not support looking at the more than one product each.
 */
enum class DiscountType {
    NONE,
    BUY_ONE_GET_ONE_FREE {
        override fun calculateDiscount(price: Price, amount: UInt): Price {
            val discountableAmount = amount / 2u
            return price * BigDecimal.valueOf(discountableAmount.toLong())
        }
    },
    TEN_PERCENT_OFF {
        override fun calculateDiscount(price: Price, amount: UInt): Price {
            val discountRate = BigDecimal.valueOf(0.1)
            val totalPrice = price * BigDecimal.valueOf(amount.toLong())
            // round to even cent values, using the usual "commercial rounding"
            return (totalPrice * discountRate).setScale(2, RoundingMode.HALF_UP)
        }
    };

    open fun calculateDiscount(price: Price, amount: UInt): Price = BigDecimal.valueOf(0)
}