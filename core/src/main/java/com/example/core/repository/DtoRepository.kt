package com.example.core.repository

import kotlinx.coroutines.flow.Flow

interface DtoRepository<T>{
    val localData: Flow<List<T>>
}