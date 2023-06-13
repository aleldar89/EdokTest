package com.example.feature_category

import android.location.Location
import androidx.lifecycle.*
import com.example.database.repo.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repo: CategoryRepo,
) : ViewModel() {

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location

    val data = repo.data.asLiveData(Dispatchers.Default)

    init {
        loadCategories()
    }

    fun saveLocation(currentLocation: Location) {
        _location.value = currentLocation
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                repo.getCategories()
            } catch (e: Exception) {
                throw e
            }
        }
    }

}