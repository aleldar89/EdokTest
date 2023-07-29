package com.example.data_dish.usecases.order_usecases

import com.example.data_dish.repository.OrderRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class GetOrderFromLocalDataUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    fun getLocalData() = orderRepository.localData
}