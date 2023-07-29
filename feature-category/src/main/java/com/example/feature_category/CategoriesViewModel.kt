package com.example.feature_category

import androidx.lifecycle.SavedStateHandle
import com.example.data_category.usecases.GetLocalDataUseCase
import com.example.data_category.usecases.GetRemoteDataUseCase
import com.example.feature_components.base_viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLocalDataUseCase: GetLocalDataUseCase,
    private val getRemoteDataUseCase: GetRemoteDataUseCase
) : BaseViewModel(savedStateHandle) {

    init {
        getRemoteData()
    }

    val localData = doLocalRequestForLiveData {
        getLocalDataUseCase.getLocalData()
    }

    private fun getRemoteData() = doNetworkRequestForData {
        getRemoteDataUseCase.getRemoteData()
    }
}