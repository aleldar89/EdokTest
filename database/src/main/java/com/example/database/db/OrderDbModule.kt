package com.example.database.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.database.dao.OrderDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OrderDbModule {

    @Singleton
    @Provides
    fun provideOrderDb(
        @ApplicationContext
        context: Context
    ) : OrderDb = Room.databaseBuilder(context, OrderDb::class.java, "order.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideOrderDao(orderDb: OrderDb) : OrderDao = orderDb.orderDao()

}