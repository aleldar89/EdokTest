package com.example.core.converter

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun toStringList(tegs: String): List<String> {
        return if (tegs.isEmpty())
            emptyList()
        else {
            tegs.split(",")
        }
    }

    @TypeConverter
    fun fromStringsList(tegs: List<String>): String = tegs
        .joinToString(separator = ",")
        .ifBlank { "" }
}