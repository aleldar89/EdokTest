package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.entity.CategoryEntity
import com.example.database.dao.CategoryDao

@Database(entities = [CategoryEntity::class], version = 1)
abstract class CategoryDb: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}