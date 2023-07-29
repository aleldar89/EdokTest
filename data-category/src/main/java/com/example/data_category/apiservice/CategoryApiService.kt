package com.example.data_category.apiservice

import com.example.data_category.dto.Category
import com.example.network.BaseApiService
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET

interface CategoryApiService: BaseApiService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    override suspend fun getAll(): LinkedTreeMap<String, List<Category>>
}