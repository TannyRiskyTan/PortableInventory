package com.example.portableinventory.data.datasource

import androidx.paging.PagingSource
import androidx.room.*
import com.example.portableinventory.domain.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product): Int

    @Query("SELECT * FROM product")
    fun pagingSource(): PagingSource<Int, Product>

    @Delete
    suspend fun deleteProduct(product: Product)
}
