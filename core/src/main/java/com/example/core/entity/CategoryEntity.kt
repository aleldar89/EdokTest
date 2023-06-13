package com.example.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.dto.Category

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image_url: String,
) {
    fun toDto() = Category(
        id = id,
        name = name,
        image_url = image_url,
    )

    companion object {
        fun fromDto(dto: Category) =
            CategoryEntity(
                id = dto.id,
                name = dto.name,
                image_url = dto.image_url,
            )
    }
}
