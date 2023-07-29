package com.example.data_dish.database

import android.content.Context
import androidx.room.Room
import com.example.data_dish.dao.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OrderDatabaseModule {

    @Singleton
    @Provides
    fun provideOrderDatabase(
        @ApplicationContext
        context: Context
    ): OrderDatabase = Room.databaseBuilder(context, OrderDatabase::class.java, "order.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideOrderDao(orderDatabase: OrderDatabase): OrderDao = orderDatabase.orderDao()
}