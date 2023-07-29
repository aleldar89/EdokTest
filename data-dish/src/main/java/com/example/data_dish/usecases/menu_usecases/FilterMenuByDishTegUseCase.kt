package com.example.data_dish.usecases.menu_usecases

import com.example.data_dish.repository.MenuRepository
import javax.inject.Inject

class FilterMenuByDishTegUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend fun filterMenuByDishTeg(teg: String) = menuRepository.filterMenuByDishTeg(teg)
}