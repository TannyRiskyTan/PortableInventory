package com.example.portableinventory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.portableinventory.domain.usecase.AddProductUseCase
import com.example.portableinventory.domain.usecase.GetProductListUseCase

class ProductListViewModelFactory(private val getProductListUseCase: GetProductListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(getProductListUseCase) as T
    }
}

class AddAndEditProductViewModelFactory(private val addProductUseCase: AddProductUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(addProductUseCase) as T
    }
}