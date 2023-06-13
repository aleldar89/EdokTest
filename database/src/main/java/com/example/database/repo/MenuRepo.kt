package com.example.database.repo

import com.example.core.dto.Dish
import kotlinx.coroutines.flow.Flow

interface MenuRepo {
    val data: Flow<List<Dish>>
    suspend fun getMenu()
    suspend fun filterMenu(teg: String)
    suspend fun clearDb()
}