package com.example.data_dish.repository

import com.example.core.repository.BaseRepository
import com.example.data_dish.apiservice.DishApiService
import com.example.data_dish.dao.MenuDao
import com.example.data_dish.dto.Dish
import com.example.data_dish.entity.toEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val dao: MenuDao,
    private val retrofit: Retrofit,
) : BaseRepository(), MenuRepository {

    private val apiService: DishApiService = retrofit.create()
    private val key = "dishes"

    override val localData: Flow<List<Dish>> = doLocalRequestForList { dao.getAll() }

    override suspend fun getRemoteData() {
        doNetworkRequest(key) {
            apiService.getMenu()
        }.map {
            dao.insert(it.toEntity())
        }
    }

    override suspend fun filterMenuByDishTeg(teg: String) {
        clearDatabaseOfMenu()
        doNetworkRequest(key) {
            apiService.getMenu()
        }.forEach { dish ->
            dish.tegs.forEach {
                if (it.contentEquals(teg))
                    dao.insert(dish.toEntity())
            }
        }
    }

    override suspend fun clearDatabaseOfMenu() {
        dao.clear()
    }
}