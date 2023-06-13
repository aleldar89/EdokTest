package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.entity.DishEntity
import com.example.database.dao.OrderDao

@Database(entities = [DishEntity::class], version = 1)
abstract class OrderDb: RoomDatabase() {
    abstract fun orderDao(): OrderDao
}