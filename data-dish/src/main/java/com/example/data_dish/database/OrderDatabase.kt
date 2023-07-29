package com.example.data_dish.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_dish.dao.OrderDao
import com.example.data_dish.entity.DishEntity

@Database(entities = [DishEntity::class], version = 1, exportSchema = false)
abstract class OrderDatabase: RoomDatabase() {
    abstract fun orderDao(): OrderDao
}