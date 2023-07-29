package com.example.feature_dish.order

import androidx.lifecycle.*
import com.example.data_dish.dto.Dish
import com.example.data_dish.usecases.order_usecases.GetOrderFromLocalDataUseCase
import com.example.data_dish.usecases.order_usecases.RemoveDishFromOrderByIdUseCase
import com.example.data_dish.usecases.order_usecases.UpdateOrderByDishIdUseCase
import com.example.feature_components.base_viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class OrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getOrderFromLocalDataUseCase: GetOrderFromLocalDataUseCase,
    private val updateOrderByDishIdUseCase: UpdateOrderByDishIdUseCase,
    private val removeDishFromOrderByIdUseCase: RemoveDishFromOrderByIdUseCase
) : BaseViewModel(savedStateHandle) {

    val localData = doLocalRequestForLiveData {
        getOrderFromLocalDataUseCase.getLocalData()
    }

    //FIXME считает неверно
    private val _orderSum = MutableLiveData(getStartOrderSum())
    val orderSum: LiveData<Int>
        get() = _orderSum

    fun plusDishToOrder(dish: Dish) {
        doLocalWork {
            updateOrderByDishIdUseCase.updateOrderByDishId(dish.id, dish.amount + 1)
            increaseOrderSum(dish.price)
        }
    }

    fun minusDishFromOrder(dish: Dish) {
        doLocalWork {
            val selectedDish = localData.value?.find { it.id == dish.id }
            if (selectedDish?.amount == 1)
                removeDishFromOrderById(dish)
            else
                updateOrderMinusDish(dish)
        }
    }

    private fun updateOrderMinusDish(dish: Dish) {
        doLocalWork {
            updateOrderByDishIdUseCase.updateOrderByDishId(dish.id, dish.amount - 1)
            decreaseOrderSum(dish.price)
        }
    }

    private fun removeDishFromOrderById(dish: Dish) {
        doLocalWork {
            removeDishFromOrderByIdUseCase.removeDishFromOrder(dish.id)
            decreaseOrderSum(dish.price)
        }
    }

    private fun increaseOrderSum(price: Int) {
        _orderSum.value = orderSum.value?.plus(price)
    }

    private fun decreaseOrderSum(price: Int) {
        _orderSum.value = orderSum.value?.minus(price)
    }

    fun nullifyOrderSum() {
        _orderSum.value = 0
    }

    private fun getStartOrderSum(): Int {
        var result = 0
        doLocalWork {
            localData.value?.forEach {
                result += it.amount * it.price
            }
        }
        return result
    }
}