package com.example.testkrishtechnolab.data.cache.network

import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import io.reactivex.Observable
import io.reactivex.Single


interface NetworkInteractor{
    fun getProductData() : Single<MutableList<ProductEntity>>
}