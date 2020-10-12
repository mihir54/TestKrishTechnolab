package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("direction")
    val direction: List<String>,
    @SerializedName("filters")
    val filters: List<Filter>,
    @SerializedName("has_more_products")
    val hasMoreProducts: Boolean,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("search_criteria")
    val searchCriteria: SearchCriteria,
    @SerializedName("sort_orders")
    val sortOrders: List<SortOrderX>,
    @SerializedName("total_count")
    val totalCount: Int
)