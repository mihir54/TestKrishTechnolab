package com.example.testkrishtechnolab.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(

    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "rating")
    val rating: Int,
    @ColumnInfo(name = "outOfStock")
    val outOfStock: Boolean,
    @ColumnInfo(name= "addedToWishlist")
    val addedToWishlist: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}