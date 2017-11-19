package com.jhughes.todoapp.data.local.converters

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime

class DateTimeConverter {

    @TypeConverter
    fun toDateTime(timestamp: Long?): DateTime? {
        return if (timestamp == null) null else DateTime(timestamp)
    }

    @TypeConverter
    fun toTimestamp(dateTime: DateTime?): Long? {

        if(dateTime == null) {
            return null
        }

        return dateTime.millis
    }
}