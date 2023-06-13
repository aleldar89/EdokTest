package com.example.database.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.database.dao.MenuDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MenuDbModule {

    @Singleton
    @Provides
    fun provideMenuDb(
        @ApplicationContext
        context: Context
    ) : MenuDb = Room.databaseBuilder(context, MenuDb::class.java, "menu.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideMenuDao(menuDb: MenuDb) : MenuDao = menuDb.menuDao()

}