package com.example.testkrishtechnolab.data.repository


import android.annotation.SuppressLint
import com.example.testkrishtechnolab.data.cache.database.DatabaseInteractor
import com.example.testkrishtechnolab.data.cache.memory.MemoryInteractor
import com.example.testkrishtechnolab.data.cache.network.NetworkInteractor
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.prefs.CustomerPreferences
import com.example.testkrishtechnolab.data.remote.ApiHelper
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val customerPreferences: CustomerPreferences,
    private val memoryInteractor: MemoryInteractor,
    private val databaseInteractor: DatabaseInteractor,
    private val networkInteractor: NetworkInteractor,

    ) {

    companion object {
        const val TAG = "ProductRepository"
    }


    fun getProductList(): Single<ProductList> = apiHelper.getProducts()

    fun removeCurrentCustomer() {
        customerPreferences.removeUserId()
        customerPreferences.removeUserName()
        customerPreferences.removeUserEmail()
        customerPreferences.removeAccessToken()
    }

    @SuppressLint("CheckResult")
    fun getAllProducts(): Observable<MutableList<ProductEntity>> {
        val memoryObservable = memoryInteractor.getProductData().toObservable()
        val databaseObservable = databaseInteractor.getProductData().toObservable()
        val networkObservable = networkInteractor.getProductData().toObservable()

        Observable.concat(memoryObservable,databaseObservable,networkObservable)
            .firstElement()
            .subscribe()

        return memoryInteractor.getProductDataObservable()

    }

}