package com.example.database.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.database.dao.CategoryDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CategoryDbModule {

    @Singleton
    @Provides
    fun provideCategoryDb(
        @ApplicationContext
        context: Context
    ) : CategoryDb = Room.databaseBuilder(context, CategoryDb::class.java, "category.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCategoryDao(categoryDb: CategoryDb) : CategoryDao = categoryDb.categoryDao()

}