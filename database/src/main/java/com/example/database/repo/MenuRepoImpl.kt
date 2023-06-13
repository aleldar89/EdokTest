package com.example.database.repo

import android.util.Log
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.example.core.dto.Dish
import com.example.core.entity.DishEntity
import com.example.database.dao.MenuDao
import com.example.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuRepoImpl @Inject constructor(
    private val menuDao: MenuDao,
    private val apiService: ApiService
) : MenuRepo {

    override val data: Flow<List<Dish>> = menuDao.getAll()
        .map { list ->
            list.map { entity ->
                entity.toDto()
            }
        }.asFlow()

    override suspend fun getMenu() {
        try {
            val response = apiService.getMenu()
            val dishes = response["dishes"] ?: throw Exception("Response body is null")
            Log.d("dishes", "$dishes")
            menuDao.insert(dishes.map(DishEntity.Companion::fromDto))
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun filterMenu(teg: String) {
        try {
            val response = apiService.getMenu()
            val dishes = response["dishes"] ?: throw Exception("Response body is null")
            clearDb()
            val result = mutableListOf<Dish>()
            dishes.forEach { dish ->
                dish.tegs.forEach {
                    if (it.contentEquals(teg))
                        result.add(dish)
                }
            }
            menuDao.insert(result.map(DishEntity.Companion::fromDto))
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun clearDb() {
        try {
            menuDao.clear()
        } catch (e: Exception) {
            throw e
        }
    }

}