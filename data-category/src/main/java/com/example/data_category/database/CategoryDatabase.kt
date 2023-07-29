package com.example.data_category.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_category.dao.CategoryDao
import com.example.data_category.entity.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class CategoryDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}