package com.example.database.repo

import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.example.core.dto.Category
import com.example.core.entity.CategoryEntity
import com.example.database.dao.CategoryDao
import com.example.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val apiService: ApiService
) : CategoryRepo {

    override val data: Flow<List<Category>> = categoryDao.getAll()
        .map { list ->
            list.map { entity ->
                entity.toDto()
            }
        }.asFlow()

    override suspend fun getCategories() {
        try {
            val response = apiService.getCategories()
            val categories = response["сategories"] ?: throw Exception("Response body is null")
            categoryDao.insert(categories.map(CategoryEntity.Companion::fromDto))
        } catch (e: Exception) {
            throw e
        }
    }

}