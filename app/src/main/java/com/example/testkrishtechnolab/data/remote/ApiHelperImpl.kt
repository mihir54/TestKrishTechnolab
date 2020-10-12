package com.example.testkrishtechnolab.data.remote

import com.example.testkrishtechnolab.data.remote.response.login.Login
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import io.reactivex.Single

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override fun loginCall(email: String, password: String): Single<Login> = apiService.loginCall(email, password)

    override fun getProducts(): Single<ProductList> = apiService.getProducts()
}