package com.example.portableinventory.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.portableinventory.R
import com.example.portableinventory.data.datasource.ProductDatabase
import com.example.portableinventory.data.repository.ProductRepositoryImpl
import com.example.portableinventory.presentation.viewmodel.ProductListViewModel
import com.example.portableinventory.presentation.viewmodel.ProductListViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        val viewModelFactory = ProductListViewModelFactory(
            ProductRepositoryImpl(ProductDatabase(this))
        )
        productListViewModel = ViewModelProvider(this, viewModelFactory).get(ProductListViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}