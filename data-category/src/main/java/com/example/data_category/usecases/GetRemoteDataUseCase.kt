package com.example.data_category.usecases

import com.example.data_category.repository.CategoryRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class GetRemoteDataUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getRemoteData() = categoryRepository.getRemoteData()
}