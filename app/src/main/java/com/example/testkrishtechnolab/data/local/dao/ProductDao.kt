package com.example.testkrishtechnolab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testkrishtechnolab.data.local.entity.ProductEntity
import io.reactivex.Maybe


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntity: List<ProductEntity>)

    @Query("SELECT * FROM product")
    fun getAllProduct() : Maybe<MutableList<ProductEntity>>
}