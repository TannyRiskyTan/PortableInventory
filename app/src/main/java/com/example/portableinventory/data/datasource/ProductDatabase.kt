package com.example.portableinventory.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.portableinventory.domain.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        const val DB_NAME = "product_db.db"
    }

}
