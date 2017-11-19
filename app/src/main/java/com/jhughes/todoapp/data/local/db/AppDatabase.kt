package com.jhughes.todoapp.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.jhughes.todoapp.data.local.dao.RoomTaskDao
import com.jhughes.todoapp.data.local.entity.RoomTask

@Database(entities = arrayOf(RoomTask::class), version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao() : RoomTaskDao
}