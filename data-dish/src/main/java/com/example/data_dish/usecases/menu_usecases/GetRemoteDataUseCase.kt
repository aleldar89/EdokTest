package com.example.data_dish.usecases.menu_usecases

import com.example.data_dish.repository.MenuRepository
import javax.inject.Inject

class GetRemoteDataUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend fun getRemoteData() = menuRepository.getRemoteData()
}