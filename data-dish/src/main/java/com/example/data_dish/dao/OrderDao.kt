package com.example.data_dish.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.core.dao.BaseDao
import com.example.data_dish.entity.DishEntity

@Dao
interface OrderDao : BaseDao<DishEntity> {

    @Query("SELECT * FROM DishEntity ORDER BY id DESC")
    override fun getAll(): LiveData<List<DishEntity>>

    @Query("DELETE FROM DishEntity WHERE id = :id")
    suspend fun removeById(id: Int)

    @Query(
        """
        UPDATE DishEntity SET
        amount = :amount
        WHERE id = :id
        """
    )
    suspend fun updateById(id: Int, amount: Int)

//FIXME увеличение количества блюда, которое уже есть в заказе
//    suspend fun save(dish: DishEntity) =
//        if (dish.id == 0)
//            insert(dish)
//        else
//            updateById(dish.id, dish.amount)
}