package com.example.core.utils

interface DataMapper<D> {

    fun entityToDto(): D
}