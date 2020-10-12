package com.example.testkrishtechnolab.di


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.testkrishtechnolab.BuildConfig
import com.example.testkrishtechnolab.data.cache.database.DatabaseInteractor
import com.example.testkrishtechnolab.data.cache.database.DatabaseInteractorImpl
import com.example.testkrishtechnolab.data.cache.memory.MemoryInteractor
import com.example.testkrishtechnolab.data.cache.memory.MemoryInteractorImpl
import com.example.testkrishtechnolab.data.cache.network.NetworkInteractor
import com.example.testkrishtechnolab.data.cache.network.NetworkInteractorImpl
import com.example.testkrishtechnolab.data.local.DatabaseService
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import com.example.testkrishtechnolab.data.model.ProductItems
import com.example.testkrishtechnolab.data.remote.ApiHelper
import com.example.testkrishtechnolab.data.remote.ApiHelperImpl
import com.example.testkrishtechnolab.data.remote.ApiService
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import com.example.testkrishtechnolab.utils.network.NetworkHelperImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

import okhttp3.*

import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule() {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
            .addInterceptor(loggingInterceptor)
            /*.addInterceptor  { chain ->
                var request = chain.request()
                request = if (networkHelper.isNetworkConnected())
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 15).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }*/
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDatabaseService(@ApplicationContext context: Context): DatabaseService =
        Room.databaseBuilder(
            context,
            DatabaseService::class.java,
            "product.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    @Provides
    @Singleton
    fun provideProductDao(databaseService: DatabaseService) = databaseService.productDao()

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper = NetworkHelperImpl(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("technolab_test", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideBehaviourSubject(): BehaviorSubject<MutableList<ProductEntity>> = BehaviorSubject.create()

    @Provides
    @Singleton
    fun provideNetworkInteractor(networkInteractor: NetworkInteractorImpl) : NetworkInteractor = networkInteractor

    @Provides
    @Singleton
    fun provideDatabaseInterator(databaseInteractor: DatabaseInteractorImpl): DatabaseInteractor = databaseInteractor

    @Provides
    @Singleton
    fun provideMemoryInteractor(memoryInteractor: MemoryInteractorImpl) : MemoryInteractor = memoryInteractor
}