package de.bitside.product

class ProductRepository {

    // usually this would use something like an ORM framework or a different service's API to pull this data from a real source
    // for this challenge just use a list of products
    private val allProductsById = listOf(
        ProductEntity.fromIdAndCents("A0001", 1299),
        ProductEntity.fromIdAndCents("A0002", 399)
    ).associateBy { p -> p.id }

    // not actually necessary right now, but usually you would probably provide a list of all products before putting something in the cart
    fun getAllProducts() = allProductsById.values

    // get the product for the id and throw an error if it is unknown
    fun getProductById(productId: String) = allProductsById[productId] ?: throw UnknowProductError(productId)
}