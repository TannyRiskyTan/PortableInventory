package com.example.portableinventory.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portableinventory.domain.model.Product
import com.example.portableinventory.domain.repository.ProductRepository
import com.example.portableinventory.util.EMPTY_STRING
import com.example.portableinventory.util.ZERO_STRING
import kotlinx.coroutines.launch

class AddAndEditProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val quantity = MutableLiveData<String>()
    val expiryDate = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val recipe = MutableLiveData<String>()
    var savingImgName = EMPTY_STRING

    init {
        quantity.value = ZERO_STRING
    }

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
            quantity.postValue(ZERO_STRING)
        }
    }

    private fun MutableLiveData<String>.getFieldValue(): String? {
        return this.value.also { postValue(EMPTY_STRING) }
    }
}