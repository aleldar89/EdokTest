package com.example.data_dish.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.core.dao.BaseEntity
import com.example.core.utils.DataMapper
import com.example.data_dish.dto.Dish
import com.example.data_dish.typeconverter.Converter

@Entity
@TypeConverters(Converter::class)
data class DishEntity(
    @PrimaryKey()
    override val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String? = null,
    val image_url: String? = null,
    val tegs: List<String>,
    val amount: Int,
) : BaseEntity(id), DataMapper<Dish> {

    override fun entityToDto() = Dish(
        id,
        name,
        price,
        weight,
        description,
        image_url,
        tegs,
        amount
    )
}

fun Dish.toEntity() = DishEntity(id, name, price, weight, description, image_url, tegs, amount)