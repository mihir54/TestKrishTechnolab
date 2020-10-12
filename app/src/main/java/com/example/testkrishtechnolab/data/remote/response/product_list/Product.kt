package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("added_to_wishlist")
    val addedToWishlist: Boolean,
    @SerializedName("best_seller")
    val bestSeller: Boolean,
    @SerializedName("flash_sale")
    val flashSale: Boolean,
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("new_item")
    val newItem: Boolean,
    @SerializedName("out_of_stock")
    val outOfStock: Boolean,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("type_id")
    val typeId: String,
    @SerializedName("url")
    val url: String
)