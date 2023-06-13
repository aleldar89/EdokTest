package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.MenuDao
import com.example.core.entity.DishEntity

@Database(entities = [DishEntity::class], version = 1)
abstract class MenuDb: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}