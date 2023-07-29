package com.example.data_category.repository

import com.example.core.repository.BaseRepository
import com.example.data_category.apiservice.CategoryApiService
import com.example.data_category.dao.CategoryDao
import com.example.data_category.dto.Category
import com.example.data_category.entity.toEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao,
    private val retrofit: Retrofit,
) : BaseRepository(), CategoryRepository {

    private val apiService: CategoryApiService = retrofit.create()
    private val key = "сategories"

    override val localData: Flow<List<Category>> = doLocalRequestForList { dao.getAll() }

    override suspend fun getRemoteData() {
        doNetworkRequest(key) {
            apiService.getAll()
        }.map {
            dao.insert(it.toEntity())
        }
    }
}