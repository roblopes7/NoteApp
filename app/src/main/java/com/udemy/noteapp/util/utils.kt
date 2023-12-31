package com.udemy.noteapp.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MM hh:mm aaa", Locale.getDefault())
    return format.format(date);
}