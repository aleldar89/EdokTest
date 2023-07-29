package com.example.data_dish.usecases.menu_usecases

import com.example.data_dish.repository.MenuRepository
import javax.inject.Inject

class GetMenuFromLocalDataUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    fun getLocalData() = menuRepository.localData
}