package com.example.data_dish.usecases.order_usecases

import com.example.data_dish.repository.OrderRepository
import javax.inject.Inject

class RemoveDishFromOrderByIdUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend fun removeDishFromOrder(id: Int) =
        orderRepository.removeDishFromOrderById(id)
}