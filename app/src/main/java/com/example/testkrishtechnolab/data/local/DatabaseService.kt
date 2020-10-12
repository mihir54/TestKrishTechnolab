package com.example.testkrishtechnolab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testkrishtechnolab.data.local.dao.ProductDao
import com.example.testkrishtechnolab.data.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    exportSchema =  false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun productDao() : ProductDao
}