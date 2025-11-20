package de.bitside.product

import java.math.BigDecimal

// define some type aliases so that required use of the individual product properties is always transparent
typealias ProductId = String
typealias Price = BigDecimal

data class ProductEntity(val id: ProductId, val price: Price) {
    companion object {
        fun fromIdAndCents(id: ProductId, cents: Long): ProductEntity {
            return ProductEntity(id, BigDecimal.valueOf(cents, 2))
        }
    }

    override fun toString(): String {
        return "$id ($price$)"
    }
}
