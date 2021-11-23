package com.example.portableinventory.domain.usecase

import androidx.paging.PagingSource
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetProductListUseCase {

    override operator fun invoke(): PagingSource<Int, Product> {
        return repository.getProductsPagingSource()
    }
}