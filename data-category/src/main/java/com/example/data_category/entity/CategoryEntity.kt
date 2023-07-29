package com.example.data_category.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.dao.BaseEntity
import com.example.core.utils.DataMapper
import com.example.data_category.dto.Category

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    val name: String,
    val image_url: String,
) : BaseEntity(id), DataMapper<Category> {

    override fun entityToDto() = Category(id, name, image_url)
}

fun Category.toEntity() = CategoryEntity(id, name, image_url)