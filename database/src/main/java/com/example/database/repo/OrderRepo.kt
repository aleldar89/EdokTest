package com.example.database.repo

import com.example.core.dto.Dish
import kotlinx.coroutines.flow.Flow

interface OrderRepo {
    val data: Flow<List<Dish>>
    suspend fun insertOrder(dish: Dish)
    suspend fun removeOrder(id: Int)
    suspend fun updateOrder(id: Int, amount: Int)
}