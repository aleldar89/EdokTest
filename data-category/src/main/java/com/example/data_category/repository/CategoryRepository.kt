package com.example.data_category.repository

import com.example.core.repository.DtoRepository
import com.example.data_category.dto.Category
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface CategoryRepository : DtoRepository<Category> {
    override val localData: Flow<List<Category>>
    suspend fun getRemoteData()
}