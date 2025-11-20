package de.bitside.product

class UnknowProductError(productId: String): Error("Unknown ProductId '${productId}'!")
