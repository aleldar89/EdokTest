package com.example.feature_dish.menu

import androidx.lifecycle.*
import com.example.data_dish.dto.Dish
import com.example.data_dish.usecases.menu_usecases.FilterMenuByDishTegUseCase
import com.example.data_dish.usecases.menu_usecases.GetMenuFromLocalDataUseCase
import com.example.data_dish.usecases.menu_usecases.GetRemoteDataUseCase
import com.example.data_dish.usecases.order_usecases.GetOrderFromLocalDataUseCase
import com.example.data_dish.usecases.order_usecases.InsertDishInOrderUseCase
import com.example.data_dish.usecases.order_usecases.UpdateOrderByDishIdUseCase
import com.example.feature_components.base_viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MenuViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMenuFromLocalDataUseCase: GetMenuFromLocalDataUseCase,
    private val getRemoteDataUseCase: GetRemoteDataUseCase,
    private val filterMenuByDishTegUseCase: FilterMenuByDishTegUseCase,
    private val getOrderFromLocalDataUseCase: GetOrderFromLocalDataUseCase,
    private val insertDishInOrderUseCase: InsertDishInOrderUseCase,
    private val updateOrderByDishIdUseCase: UpdateOrderByDishIdUseCase,
) : BaseViewModel(savedStateHandle) {

    init {
        getMenuFromRemoteData()
    }

    private val _tegs = MutableLiveData(listOf<String>())
    val tegs: LiveData<List<String>>
        get() = _tegs

    val menuLocalData = doLocalRequestForLiveData {
        getMenuFromLocalDataUseCase.getLocalData()
    }

    private val orderLocalData = doLocalRequestForLiveData {
        getOrderFromLocalDataUseCase.getLocalData()
    }

    private fun getMenuFromRemoteData() = doNetworkRequestForData {
        getRemoteDataUseCase.getRemoteData()
        loadTegs()
    }

    private fun loadTegs() {
        val result = mutableSetOf<String>()
        menuLocalData.value?.forEach {
            result.addAll(it.tegs)
        }
        _tegs.value = result.toList()
    }

    fun filterMenuByTeg(teg: String) {
        doLocalWork {
            filterMenuByDishTegUseCase.filterMenuByDishTeg(teg)
        }
    }

    fun addDishToOrder(dish: Dish) {
        doLocalWork {
            val currentDish = orderLocalData.value?.find { it.id == dish.id }
            if (currentDish != null)
                updateOrder(currentDish.id, currentDish.amount + 1)
            else
                insertDishInOrder(dish.copy(amount = 1))
        }
    }

    private fun insertDishInOrder(dish: Dish) {
        doLocalWork {
            insertDishInOrderUseCase.insertDishInOrder(dish)
        }
    }

    private fun updateOrder(id: Int, amount: Int) {
        doLocalWork {
            updateOrderByDishIdUseCase.updateOrderByDishId(id, amount)
        }
    }
}
