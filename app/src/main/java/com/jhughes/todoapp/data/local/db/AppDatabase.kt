package com.jhughes.todoapp.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.jhughes.todoapp.data.local.converters.DateTimeConverter
import com.jhughes.todoapp.data.local.dao.TaskEntityDao
import com.jhughes.todoapp.data.local.entity.TaskEntity

@Database(entities = arrayOf(TaskEntity::class), version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_database"
    }

    abstract fun taskDao() : TaskEntityDao
}