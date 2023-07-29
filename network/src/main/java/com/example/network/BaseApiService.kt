package com.example.network

import retrofit2.http.GET

interface BaseApiService {

    @GET
    suspend fun getAll(): Any
}