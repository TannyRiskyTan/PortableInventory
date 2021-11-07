package com.example.portableinventory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.portableinventory.domain.repository.ProductRepository
import com.example.portableinventory.domain.usecase.AddProductUseCase

class ProductListViewModelFactory(private val repo: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(repo) as T
    }
}

class AddAndEditProductViewModelFactory(private val addProductUseCase: AddProductUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(addProductUseCase) as T
    }
}