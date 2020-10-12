package com.example.testkrishtechnolab.data.cache.database

import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import io.reactivex.Maybe


interface DatabaseInteractor {
    fun getProductData() : Maybe<MutableList<ProductEntity>>
    fun saveProductData(productEntity: MutableList<ProductEntity>)
}