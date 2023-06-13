package com.example.database.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface CategoryRepoModule {

    @Singleton
    @Binds
    fun provideCategoryRepo(impl: CategoryRepoImpl): CategoryRepo
}