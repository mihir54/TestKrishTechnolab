package com.example.testkrishtechnolab.data.remote

import com.example.testkrishtechnolab.data.remote.response.login.Login
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import io.reactivex.Single


interface ApiHelper {

    fun loginCall(
        email : String,
       password : String
    ) : Single<Login>

    fun getProducts() : Single<ProductList>
}