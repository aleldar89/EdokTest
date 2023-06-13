package com.example.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.core.dto.Dish
import com.example.core.converter.Converter

@Entity
@TypeConverters(Converter::class)
data class DishEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String? = null,
    val image_url: String? = null,
    val tegs: List<String>,
    val amount: Int,
) {
    fun toDto() = Dish(
        id = id,
        name = name,
        price = price,
        weight = weight,
        description = description,
        image_url = image_url,
        tegs = tegs,
        amount = amount,
    )

    companion object {
        fun fromDto(dto: Dish) =
            DishEntity(
                id = dto.id,
                name = dto.name,
                price = dto.price,
                weight = dto.weight,
                description = dto.description,
                image_url = dto.image_url,
                tegs = dto.tegs,
                amount = dto.amount,
            )
    }
}