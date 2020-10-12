package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class FilterGroup(
    @SerializedName("filters")
    val filters: List<FilterX>
)