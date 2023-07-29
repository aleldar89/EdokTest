package com.example.data_dish.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.core.dao.BaseDao
import com.example.data_dish.entity.DishEntity

@Dao
interface MenuDao : BaseDao<DishEntity> {

    @Query("SELECT * FROM DishEntity ORDER BY id DESC")
    override fun getAll(): LiveData<List<DishEntity>>

    @Query("DELETE FROM DishEntity")
    suspend fun clear()
}