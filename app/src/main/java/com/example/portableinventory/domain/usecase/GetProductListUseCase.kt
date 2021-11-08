package com.example.portableinventory.domain.usecase

import androidx.paging.PagingSource
import com.example.portableinventory.domain.model.Product

interface GetProductListUseCase {
    operator fun invoke(): PagingSource<Int, Product>
}