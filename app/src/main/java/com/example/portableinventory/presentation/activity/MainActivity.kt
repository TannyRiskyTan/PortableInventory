package com.example.portableinventory.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.portableinventory.R
import com.example.portableinventory.data.datasource.ProductDatabase
import com.example.portableinventory.data.repository.ProductRepositoryImpl
import com.example.portableinventory.presentation.viewmodel.ProductViewModel
import com.example.portableinventory.presentation.viewmodel.ProductViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = ProductViewModelFactory(
            ProductRepositoryImpl(ProductDatabase(this))
        )
        productViewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
    }
}