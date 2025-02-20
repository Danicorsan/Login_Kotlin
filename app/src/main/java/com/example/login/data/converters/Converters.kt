package com.example.login.data.converters

import androidx.room.TypeConverter
import com.example.login.data.model.Email
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toEmail(value:String?):Email?{
        return value?.let { Email(it) }
    }

    @TypeConverter
    fun fromEmail(value:Email?):String?{
        return value?.value
    }
}