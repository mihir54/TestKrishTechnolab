package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class Filter(
    @SerializedName("code")
    val code: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("options")
    val options: List<Option>
)