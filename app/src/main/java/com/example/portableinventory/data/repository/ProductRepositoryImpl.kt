package com.example.portableinventory.data.repository

import androidx.paging.PagingSource
import com.example.portableinventory.data.datasource.ProductDatabase
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository

class ProductRepositoryImpl(
    db: ProductDatabase
): ProductRepository {

    private val dao = db.getProductDao()

    override suspend fun insert(product: Product) =
        dao.insert(product)

    override suspend fun update(product: Product) =
        dao.insert(product)

    override fun getProductsPagingSource(): PagingSource<Int, Product> =
        dao.pagingSource()

    override suspend fun deleteProduct(product: Product) =
        dao.deleteProduct(product)

}