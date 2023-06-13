package com.example.database.repo

import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.example.core.dto.Dish
import com.example.core.entity.DishEntity
import com.example.database.dao.OrderDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepoImpl @Inject constructor(
    private val orderDao: OrderDao,
) : OrderRepo {

    override val data: Flow<List<Dish>> = orderDao.getAll()
        .map { list ->
            list.map { entity ->
                entity.toDto()
            }
        }.asFlow()

    override suspend fun insertOrder(dish: Dish) {
        try {
            orderDao.insert(DishEntity.Companion.fromDto(dish))
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun removeOrder(id: Int) {
        try {
            orderDao.removeById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateOrder(id: Int, amount: Int) {
        try {
            orderDao.updateById(id, amount)
        } catch (e: Exception) {
            throw e
        }
    }

}