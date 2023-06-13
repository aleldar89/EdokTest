package com.example.database.repo

import com.example.core.dto.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {
    val data: Flow<List<Category>>
    suspend fun getCategories()
}