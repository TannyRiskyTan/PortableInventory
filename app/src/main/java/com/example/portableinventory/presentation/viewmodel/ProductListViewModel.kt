package com.example.portableinventory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.portableinventory.domain.usecase.GetProductListUseCase

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 6)
    ) {
        getProductListUseCase.invoke()
    }.flow.cachedIn(viewModelScope)

}

