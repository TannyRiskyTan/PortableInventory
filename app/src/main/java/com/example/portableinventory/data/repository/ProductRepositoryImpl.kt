package com.example.portableinventory.data.repository

import androidx.paging.PagingSource
import com.example.portableinventory.data.datasource.ProductDao
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val dao: ProductDao
) : ProductRepository {

    override suspend fun insert(product: Product) =
        dao.insert(product)

    override suspend fun update(product: Product) =
        dao.update(product)

    override fun getProductsPagingSource(): PagingSource<Int, Product> =
        dao.pagingSource()

    override suspend fun delete(product: Product) =
        dao.deleteProduct(product)

}