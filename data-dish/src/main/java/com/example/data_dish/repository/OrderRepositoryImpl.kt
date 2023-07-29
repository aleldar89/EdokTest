package com.example.data_dish.repository

import com.example.core.repository.BaseRepository
import com.example.data_dish.dao.OrderDao
import com.example.data_dish.dto.Dish
import com.example.data_dish.entity.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val dao: OrderDao,
) : BaseRepository(), OrderRepository {

    override val localData: Flow<List<Dish>> = doLocalRequestForList { dao.getAll() }

    override suspend fun insertDishInOrder(dish: Dish) {
        dao.insert(dish.toEntity())
    }

    override suspend fun removeDishFromOrderById(id: Int) {
        dao.removeById(id)
    }

    override suspend fun updateOrderByDishId(id: Int, amount: Int) {
        dao.updateById(id, amount)
    }
}