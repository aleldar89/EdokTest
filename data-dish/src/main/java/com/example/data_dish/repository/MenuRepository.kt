package com.example.data_dish.repository

import com.example.core.repository.DtoRepository
import com.example.data_dish.dto.Dish
import kotlinx.coroutines.flow.Flow

interface MenuRepository : DtoRepository<Dish> {
    override val localData: Flow<List<Dish>>
    suspend fun getRemoteData()
    suspend fun filterMenuByDishTeg(teg: String)
    suspend fun clearDatabaseOfMenu()
}