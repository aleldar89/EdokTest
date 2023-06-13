package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CategoryEntity>)
}