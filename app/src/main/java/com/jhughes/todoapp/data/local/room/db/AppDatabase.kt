package com.jhughes.todoapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhughes.todoapp.data.local.room.converters.DateTimeConverter
import com.jhughes.todoapp.data.local.room.dao.LiveDataTaskEntityDao
import com.jhughes.todoapp.data.local.room.dao.TaskEntityDao
import com.jhughes.todoapp.data.local.entity.TaskEntity

@Database(entities = arrayOf(TaskEntity::class), version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_database"
    }

    abstract fun taskDao() : TaskEntityDao

    abstract fun liveDataTaskDao() : LiveDataTaskEntityDao
}