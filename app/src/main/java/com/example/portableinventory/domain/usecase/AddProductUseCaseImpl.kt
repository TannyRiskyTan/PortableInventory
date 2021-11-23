package com.example.portableinventory.domain.usecase

import android.util.Log
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository
import com.example.portableinventory.util.UNKNOWN_EXCEPTION
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : AddProductUseCase {

    override suspend operator fun invoke(product: Product) {
        try {
            repository.insert(product)
        } catch (addProductException: Exception) {
            Log.d(
                AddProductUseCaseImpl::class.simpleName,
                addProductException.message ?: UNKNOWN_EXCEPTION
            )
        }
    }
}