package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.entity.DishEntity

@Dao
interface MenuDao {

    @Query("SELECT * FROM DishEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<DishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menu: List<DishEntity>)

    @Query("DELETE FROM DishEntity")
    suspend fun clear()
}