package com.example.testkrishtechnolab.data.model

data class ProductItems(
    val name: String,
    val price: Double,
    val image: String,
    val rating: Int,
    val outOfStock: Boolean,
    val addedToWishlist: Boolean
)