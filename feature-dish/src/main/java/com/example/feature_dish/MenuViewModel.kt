package com.example.feature_dish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.dto.Dish
import com.example.database.repo.MenuRepo
import com.example.database.repo.OrderRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepo: MenuRepo,
    private val orderRepo: OrderRepo,
) : ViewModel() {

    val menuData = menuRepo.data.asLiveData(Dispatchers.Default)
    private val orderData = orderRepo.data.asLiveData(Dispatchers.Default)

    private val _tegs = MutableLiveData(listOf<String>())
    val tegs: LiveData<List<String>>
        get() = _tegs

    init {
        loadDishes()
    }

    private fun loadDishes() {
        viewModelScope.launch {
            try {
                menuRepo.getMenu()
                loadTegs()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun loadTegs() {
        viewModelScope.launch {
            try {
                val result = mutableSetOf<String>()
                menuData.value?.forEach {
                    result.addAll(it.tegs)
                }
                _tegs.value = result.toList()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun filterMenu(teg: String) {
        viewModelScope.launch {
            try {
                menuRepo.filterMenu(teg)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun orderPlus(dish: Dish) {
        viewModelScope.launch {
            try {
//                val result = orderData.value?.any { it.id == dish.id }
                val currentDish = orderData.value?.find { it.id == dish.id }
//                if (result == true)
                println(currentDish?.amount)
                if (currentDish != null)
//                    updateOrder(dish)
                    updateOrder(currentDish, currentDish.amount + 1)
                else
                    addOrder(dish.copy(amount = 1))
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun addOrder(dish: Dish) {
        viewModelScope.launch {
            try {
                orderRepo.insertOrder(dish)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun updateOrder(dish: Dish, amount: Int) {
        viewModelScope.launch {
            try {
                //todo dish.amount + 1 всегда равно 1
                orderRepo.updateOrder(dish.id, amount)
            } catch (e: Exception) {
                throw e
            }
        }
    }

}