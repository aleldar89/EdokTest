package com.example.feature_order

import android.location.Location
import androidx.lifecycle.*
import com.example.core.dto.Dish
import com.example.database.repo.OrderRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepo: OrderRepo,
) : ViewModel() {

    val orderData = orderRepo.data.asLiveData(Dispatchers.Default)

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location

    private val _sum = MutableLiveData(0)
    val sum: LiveData<Int>
        get() = _sum

    fun cleanSum() {
        _sum.value = 0
    }

    fun orderPlus(dish: Dish) {
        viewModelScope.launch {
            try {
                val result = orderData.value?.any { it.id == dish.id }
                if (result == true)
                    updatePlusOrder(dish)
                else
                    addOrder(dish)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun orderMinus(dish: Dish) {
        viewModelScope.launch {
            try {
                val result = orderData.value?.find { it.id == dish.id }
                if (result?.amount == 1)
                    removeOrder(dish)
                else
                    updateMinusOrder(dish)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun addOrder(dish: Dish) {
        viewModelScope.launch {
            try {
                orderRepo.insertOrder(dish)
                _sum.value = sum.value?.plus(dish.price)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun removeOrder(dish: Dish) {
        viewModelScope.launch {
            try {
                orderRepo.removeOrder(dish.id)
                _sum.value = sum.value?.minus(dish.price)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun updatePlusOrder(dish: Dish) {
        viewModelScope.launch {
            try {
                orderRepo.updateOrder(dish.id, dish.amount + 1)
                _sum.value = sum.value?.plus(dish.price)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun updateMinusOrder(dish: Dish) {
        viewModelScope.launch {
            try {
                orderRepo.updateOrder(dish.id, dish.amount - 1)
                _sum.value = sum.value?.minus(dish.price)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun saveLocation(currentLocation: Location) {
        _location.value = currentLocation
    }

}