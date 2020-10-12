package com.example.testkrishtechnolab.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Customer(
    @Expose
    @SerializedName("userId")
    val id: Int,

    @Expose
    @SerializedName("userName")
    val name: String,

    @Expose
    @SerializedName("userEmail")
    val email: String,

    @Expose
    @SerializedName("accessToken")
    val accessToken: String
)