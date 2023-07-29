package com.example.data_dish.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface OrderRepositoryModule {

    @Singleton
    @Binds
    fun bindsOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}