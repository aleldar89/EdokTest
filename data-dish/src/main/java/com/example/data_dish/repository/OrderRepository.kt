package com.example.data_dish.repository

import com.example.core.repository.DtoRepository
import com.example.data_dish.dto.Dish
import kotlinx.coroutines.flow.Flow

interface OrderRepository : DtoRepository<Dish> {
    override val localData: Flow<List<Dish>>
    suspend fun insertDishInOrder(dish: Dish)
    suspend fun removeDishFromOrderById(id: Int)
    suspend fun updateOrderByDishId(id: Int, amount: Int)
}