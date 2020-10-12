package com.example.testkrishtechnolab.data.cache.database

import android.util.Log
import com.example.testkrishtechnolab.data.cache.memory.MemoryInteractor
import com.example.testkrishtechnolab.data.local.dao.ProductDao
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import io.reactivex.Maybe
import io.reactivex.Maybe.empty
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DatabaseInteractorImpl @Inject constructor(
    private val productDao: ProductDao,
    private val memoryInteractor: MemoryInteractor
) : DatabaseInteractor {

    companion object{
        const val TAG = "DatabaseInteractorImpl"
    }

    override fun getProductData(): Maybe<MutableList<ProductEntity>>
    {
        Log.d(TAG, "getProductData: ")
        return productDao.getAllProduct()
            .subscribeOn(Schedulers.io())
            .filter{ products ->
                 products.size > 0
            }
            .doOnSuccess(memoryInteractor::saveProductData)
    }

    override fun saveProductData(productEntity: MutableList<ProductEntity>) {
        productDao.insertProduct(productEntity)
    }
}