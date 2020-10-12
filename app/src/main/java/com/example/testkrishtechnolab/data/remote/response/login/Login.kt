package com.example.testkrishtechnolab.data.remote.response.login


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("address")
    val address: Any,
    @SerializedName("cart_item_count")
    val cartItemCount: Int,
    @SerializedName("customer_token")
    val customerToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("quote_id")
    val quoteId: Int,
    @SerializedName("referral_url")
    val referralUrl: String,
    @SerializedName("swell_points")
    val swellPoints: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("website_id")
    val websiteId: Int
)