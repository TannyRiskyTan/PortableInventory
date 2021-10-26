package com.example.portableinventory.domain.repository

import androidx.paging.PagingSource
import com.example.portableinventory.domain.model.Product

interface ProductRepository {

    suspend fun insert(product: Product): Long

    suspend fun update(product: Product): Long

    fun getProductsPagingSource(): PagingSource<Int, Product>

    suspend fun deleteProduct(product: Product)
}