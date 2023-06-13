package com.example.network

import com.example.core.dto.Category
import com.example.core.dto.Dish
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET

interface ApiService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): LinkedTreeMap<String, List<Category>>

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getMenu(): LinkedTreeMap<String, List<Dish>>

}