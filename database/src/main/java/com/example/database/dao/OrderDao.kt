package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.entity.DishEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM DishEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<DishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: DishEntity)

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

    //todo сделать увеличение количества блюда, которое уже есть в заказе
//    suspend fun save(dish: DishEntity) =
//        if (dish.id == 0)
//            insert(dish)
//        else
//            updateById(dish.id, dish.amount)

}