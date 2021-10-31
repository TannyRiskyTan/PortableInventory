package com.example.portableinventory.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val id = ObservableField<String>()
    val name = ObservableField<String>()
    val quantity = ObservableField<String>()
    val expiryDate = ObservableField<String>()
    val category = ObservableField<String>()
    val recipe = ObservableField<String>()
    var savingImgName = ""

    init {
        quantity.set("0")
    }

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 6)
    ) {
        repository.getProductsPagingSource()
    }.flow.cachedIn(viewModelScope)

    fun saveProduct() {
        viewModelScope.launch {
            repository.insert(
                Product(
                    id = id.getFieldValue()?.toLong(),
                    name = name.getFieldValue(),
                    quantity = quantity.getFieldValue()?.toInt(),
                    expiryDate = expiryDate.getFieldValue(),
                    imgName = savingImgName,
                    category = category.getFieldValue(),
                    recipe = recipe.getFieldValue()
                )
            )
            quantity.set("0")
        }
    }

    private fun ObservableField<String>.getFieldValue(): String? {
        return this.get().also { set("") }
    }
}

