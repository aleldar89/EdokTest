package com.example.data_dish.usecases.order_usecases

import com.example.data_dish.repository.OrderRepository
import javax.inject.Inject

class UpdateOrderByDishIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend fun updateOrderByDishId(id: Int, amount: Int) =
        orderRepository.updateOrderByDishId(id, amount)
}