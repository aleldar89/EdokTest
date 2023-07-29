package com.example.data_category.usecases

import com.example.data_category.repository.CategoryRepository
import javax.inject.Inject

class GetLocalDataUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    fun getLocalData() = categoryRepository.localData
}