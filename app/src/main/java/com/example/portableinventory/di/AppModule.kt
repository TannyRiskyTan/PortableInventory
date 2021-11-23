package com.example.portableinventory.di

import android.app.Application
import androidx.room.Room
import com.example.portableinventory.data.datasource.ProductDao
import com.example.portableinventory.data.datasource.ProductDatabase
import com.example.portableinventory.data.datasource.ProductDatabase.Companion.DB_NAME
import com.example.portableinventory.data.repository.ProductRepositoryImpl
import com.example.portableinventory.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideProductDatabase(
        application: Application
    ): ProductDatabase {
        return Room.databaseBuilder(
            application,
            ProductDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideProductDao(
        db: ProductDatabase
    ): ProductDao {
        return db.getProductDao()
    }

    @Singleton
    @Provides
    fun bindProductRepository(
        dao: ProductDao
    ): ProductRepository {
        return ProductRepositoryImpl(dao)
    }
}