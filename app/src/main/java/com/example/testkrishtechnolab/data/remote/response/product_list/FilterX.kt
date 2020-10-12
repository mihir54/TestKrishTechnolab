package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class FilterX(
    @SerializedName("condition_type")
    val conditionType: String,
    @SerializedName("field")
    val `field`: String,
    @SerializedName("value")
    val value: String
)