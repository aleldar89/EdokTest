package com.example.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import com.example.core.utils.DataMapper
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

abstract class BaseRepository {

    protected fun <E : DataMapper<D>, D> doLocalRequestForList(
        request: () -> LiveData<List<E>>
    ): Flow<List<D>> = request().map { list ->
        list.map {
            it.entityToDto()
        }
    }.asFlow().catch { exception ->
        throw exception
    }

    protected suspend fun <K, D> doNetworkRequest(
        key: K,
        request: suspend () -> LinkedTreeMap<K, List<D>>
    ): List<D> = request()[key] ?: throw Exception("Response body is null")
}