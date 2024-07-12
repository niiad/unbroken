package com.niiad.unbroken.domain.entities.shared

data class SharedProduct (
    var id: Long,
    var name: String,
    var imageUrl: String,
    var category: String,
    var description: String,
    var sku: String,
    var barcode: String,
    var brand: String,
    var unitPrice: String,
    var quantityInStock: Int,
    var supplier: String,
    var city: String,
    var region: String,
    var country: String
)
