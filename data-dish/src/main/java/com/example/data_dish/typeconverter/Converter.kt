package com.example.data_dish.typeconverter

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun toListOfString(tegs: String): List<String> {
        return if (tegs.isEmpty())
            emptyList()
        else {
            tegs.split(",")
        }
    }

    @TypeConverter
    fun fromListOfString(tegs: List<String>): String = tegs
        .joinToString(separator = ",")
        .ifBlank { "" }
}