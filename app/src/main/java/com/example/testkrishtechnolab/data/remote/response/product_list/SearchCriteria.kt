package com.example.testkrishtechnolab.data.remote.response.product_list


import com.google.gson.annotations.SerializedName

data class SearchCriteria(
    @SerializedName("filter_groups")
    val filterGroups: List<FilterGroup>,
    @SerializedName("sort_orders")
    val sortOrders: List<SortOrder>
)