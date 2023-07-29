package com.example.data_dish.apiservice

import com.example.data_dish.dto.Dish
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET

interface DishApiService {

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getMenu(): LinkedTreeMap<String, List<Dish>>
}