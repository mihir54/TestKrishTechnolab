package com.example.testkrishtechnolab.ui.product

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import com.example.testkrishtechnolab.data.remote.response.product_list.ProductList
import com.example.testkrishtechnolab.data.repository.ProductRepository
import com.example.testkrishtechnolab.ui.base.BaseViewModel
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

import retrofit2.Response

class ProductViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    networkHelper: NetworkHelper,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable,networkHelper) {

    private val productItemsList = mutableListOf<ProductEntity>()
    val liveDataProducts = MutableLiveData<List<ProductEntity>>()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()


    fun getProducts(){
        if (checkInternetConnectionWithMessage()) {
            loggingIn.postValue(true)
            compositeDisposable.add(
                productRepository.getAllProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        liveDataProducts.postValue(it)
                    }
                    .subscribe({
                        loggingIn.postValue(false)
                    }, { e ->
                        handleNetworkError(e)
                        loggingIn.postValue(false)
                    })
            )
        }
    }



    fun getProducts1(){
        if (checkInternetConnectionWithMessage()) {
            loggingIn.postValue(true)
            compositeDisposable.add(
                productRepository.getProductList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { p->
                        for (item in p.products){
                            productItemsList.add(
                                ProductEntity(
                                    item.name,
                                    item.price,
                                    item.image,
                                    item.rating,
                                    item.outOfStock,
                                    item.addedToWishlist
                                )
                            )
                        }
                        liveDataProducts.postValue(productItemsList)
                    }
                    .subscribe(
                        {
                            loggingIn.postValue(false)
                        },{ error ->
                            handleNetworkError(error)
                            loggingIn.postValue(false)
                        }
                    )
            )
        }
    }

    fun logout() = productRepository.removeCurrentCustomer()

    override fun onCreate() {
        TODO("Not yet implemented")
    }
}