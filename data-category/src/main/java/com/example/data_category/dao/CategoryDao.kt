package com.example.data_category.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.core.dao.BaseDao
import com.example.data_category.entity.CategoryEntity

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {

    @Query("SELECT * FROM CategoryEntity ORDER BY id DESC")
    override fun getAll(): LiveData<List<CategoryEntity>>
}