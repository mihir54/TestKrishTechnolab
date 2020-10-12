package com.example.testkrishtechnolab.data.remote

import com.example.testkrishtechnolab.data.remote.response.login.Login
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import io.reactivex.Single

import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(EndPoints.LOGIN)
    fun loginCall(
        @Query("email") email : String,
        @Query("password") password : String
    ) : Single<Login>

    @POST(EndPoints.PRODUCT)
    fun getProducts() : Single<ProductList>
}