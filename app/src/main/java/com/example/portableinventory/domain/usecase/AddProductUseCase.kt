package com.example.portableinventory.domain.usecase

import com.example.portableinventory.domain.model.Product

interface AddProductUseCase {
    suspend operator fun invoke(product: Product)
}