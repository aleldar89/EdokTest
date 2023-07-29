package com.example.data_category.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface CategoryRepositoryModule {

    @Singleton
    @Binds
    fun bindsCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}