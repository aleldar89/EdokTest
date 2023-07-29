package com.example.data_category.dto

import com.example.core.FeedItem

data class Category(
    override val id: Int,
    val name: String,
    val image_url: String,
): FeedItem