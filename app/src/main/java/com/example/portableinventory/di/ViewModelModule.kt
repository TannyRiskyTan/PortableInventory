package com.example.portableinventory.di

import com.example.portableinventory.domain.usecase.AddProductUseCase
import com.example.portableinventory.domain.usecase.AddProductUseCaseImpl
import com.example.portableinventory.domain.usecase.GetProductListUseCase
import com.example.portableinventory.domain.usecase.GetProductListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindGetProductListUseCase(
        getProductListUseCaseImpl: GetProductListUseCaseImpl
    ): GetProductListUseCase

    @Binds
    abstract fun bindAddProductUseCase(
        addProductUseCaseImpl: AddProductUseCaseImpl
    ): AddProductUseCase
}