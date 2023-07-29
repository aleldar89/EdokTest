package com.example.data_category.database

import android.content.Context
import androidx.room.Room
import com.example.data_category.dao.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CategoryDatabaseModule {

    @Singleton
    @Provides
    fun provideCategoryDatabase(
        @ApplicationContext
        context: Context
    ): CategoryDatabase = Room.databaseBuilder(context, CategoryDatabase::class.java, "category.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCategoryDao(categoryDb: CategoryDatabase): CategoryDao = categoryDb.categoryDao()
}