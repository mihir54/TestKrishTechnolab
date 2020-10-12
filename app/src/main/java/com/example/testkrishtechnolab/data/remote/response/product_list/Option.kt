package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("count")
    val count: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("to")
    val to: String
)