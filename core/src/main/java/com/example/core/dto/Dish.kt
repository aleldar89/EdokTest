package com.example.core.dto

data class Dish(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String? = null,
    val image_url: String? = null,
    val tegs: List<String>,
    val amount: Int = 0
)