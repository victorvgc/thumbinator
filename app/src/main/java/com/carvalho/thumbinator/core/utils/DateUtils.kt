package com.carvalho.thumbinator.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format:String = "dd/MM/yy"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}

fun Long.formatAsDate(dateInMillis: Long, format:String = "dd/MM/yy"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(dateInMillis)
}