package com.example.core.listener

interface OnInteractionListener<T> {
    fun onSelect(item: T) {}
    fun onAdd(item: T) {}
    fun onRemove(item: T) {}
}