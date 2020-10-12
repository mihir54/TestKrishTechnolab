package com.example.testkrishtechnolab.data.cache.network

import android.annotation.SuppressLint
import android.util.Log
import com.example.testkrishtechnolab.data.cache.database.DatabaseInteractor
import com.example.testkrishtechnolab.data.cache.memory.MemoryInteractor
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import com.example.testkrishtechnolab.data.remote.ApiHelper
import com.example.testkrishtechnolab.data.remote.ApiService
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log


class NetworkInteractorImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val memoryInteractor: MemoryInteractor,
    private val databaseInteractor: DatabaseInteractor

) : NetworkInteractor{

    companion object{
        const val TAG= "NetworkInteractorImpl"
    }

    private val productList = mutableListOf<ProductEntity>()

    override fun getProductData(): Single<MutableList<ProductEntity>> =

            apiHelper.getProducts()
                .subscribeOn(Schedulers.io())
                .map {
                    Log.d(TAG, "getProductData: ${it.products.size}")
                    for (product in it.products) {
                        productList.add(
                            ProductEntity(
                                product.name,
                                product.price,
                                product.image,
                                product.rating,
                                product.outOfStock,
                                product.addedToWishlist
                            )
                        )
                    }
                    productList
                }
                .doOnSuccess(databaseInteractor::saveProductData)
                .doOnSuccess(memoryInteractor::saveProductData)


    }

