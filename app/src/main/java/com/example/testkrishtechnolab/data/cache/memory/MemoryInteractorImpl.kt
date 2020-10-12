package com.example.testkrishtechnolab.data.cache.memory

import android.util.Log
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

import javax.inject.Inject

class MemoryInteractorImpl @Inject constructor(
    private val observable : BehaviorSubject<MutableList<ProductEntity>>
) : MemoryInteractor{

    companion object{
        const val TAG = "MemoryInteractorImpl"
    }

    private lateinit var productItems: MutableList<ProductEntity>

    override fun saveProductData(productItems: MutableList<ProductEntity>) {
        this.productItems = productItems
        observable.onNext(productItems)
    }

    override fun getProductData(): Maybe<MutableList<ProductEntity>> =
        if (this::productItems.isInitialized) {
            Log.d(TAG, "getProductData: ${productItems.size}")
            Maybe.just(productItems)
        } else{

            Maybe.empty()
        }

    override fun getProductDataObservable(): Observable<MutableList<ProductEntity>> {
        if (this::productItems.isInitialized) {
            Log.d(TAG, "getProductDataObservable: ${productItems.size}")
        }

        return observable
    }
}