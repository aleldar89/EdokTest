package com.example.feature_components.utils

import java.text.SimpleDateFormat
import java.util.*

//FIXME конвертирует неверно
fun getFormattedDate(datePattern: String): String {
    val formatter = SimpleDateFormat(datePattern, Locale.getDefault()).toString()
    return formatter.format(Calendar.getInstance().time)
}