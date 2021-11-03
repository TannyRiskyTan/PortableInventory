package com.example.portableinventory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.portableinventory.domain.repository.ProductRepository

class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 6)
    ) {
        repository.getProductsPagingSource()
    }.flow.cachedIn(viewModelScope)

}

