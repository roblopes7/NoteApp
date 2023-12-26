package com.udemy.noteapp.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date

@ProvidedTypeConverter
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}