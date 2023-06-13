package com.example.database.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface MenuRepoModule {

    @Singleton
    @Binds
    fun provideMenuRepo(impl: MenuRepoImpl): MenuRepo
}