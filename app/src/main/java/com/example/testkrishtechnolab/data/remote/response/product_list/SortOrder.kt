package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class SortOrder(
    @SerializedName("direction")
    val direction: String,
    @SerializedName("field")
    val `field`: String
)