package com.example.testkrishtechnolab.data.cache.memory

import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import io.reactivex.Maybe
import io.reactivex.Observable


interface MemoryInteractor {

    fun saveProductData(productItems: MutableList<ProductEntity>)
    fun getProductData(): Maybe<MutableList<ProductEntity>>
    fun getProductDataObservable() : Observable<MutableList<ProductEntity>>

}