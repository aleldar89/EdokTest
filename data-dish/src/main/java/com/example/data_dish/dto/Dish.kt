package com.example.data_dish.dto

import com.example.core.FeedItem

data class Dish(
    override val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String? = null,
    val image_url: String? = null,
    val tegs: List<String>,
    val amount: Int = 0
) : FeedItem