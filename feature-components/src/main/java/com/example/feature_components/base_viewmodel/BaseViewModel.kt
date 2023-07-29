package com.example.feature_components.base_viewmodel

import android.location.Location
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val keyLocation = "key_location"
//    private val _location = MutableLiveData<Location>(checkNotNull(savedStateHandle[keyLocation]))
//    val location: LiveData<Location>
//        get() = _location

    protected fun <T> doLocalRequestForLiveData(
        request: () -> Flow<List<T>>
    ): LiveData<List<T>> = request().asLiveData()

    protected fun doNetworkRequestForData(
        request: suspend () -> Unit
    ) = try {
        viewModelScope.launch {
            request()
        }
    } catch (e: Exception) {
        throw e
    }

    protected fun doLocalWork(
        request: suspend () -> Unit
    ) = try {
        viewModelScope.launch {
            request()
        }
    } catch (e: Exception) {
        throw e
    }
}