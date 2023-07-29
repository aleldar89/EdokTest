package com.example.data_dish.database

import android.content.Context
import androidx.room.Room
import com.example.data_dish.dao.MenuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MenuDatabaseModule {

    @Singleton
    @Provides
    fun provideMenuDatabase(
        @ApplicationContext
        context: Context
    ): MenuDatabase = Room.databaseBuilder(context, MenuDatabase::class.java, "menu.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideMenuDao(menuDatabase: MenuDatabase): MenuDao = menuDatabase.menuDao()
}