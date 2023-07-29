package com.example.data_dish.usecases.order_usecases

import com.example.data_dish.dto.Dish
import com.example.data_dish.repository.OrderRepository
import javax.inject.Inject

class InsertDishInOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend fun insertDishInOrder(dish: Dish) = orderRepository.insertDishInOrder(dish)
}