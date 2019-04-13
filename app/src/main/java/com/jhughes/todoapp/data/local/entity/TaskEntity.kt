package com.jhughes.todoapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhughes.todoapp.data.local.room.dao.TaskEntityDao
import org.joda.time.DateTime

@Entity(tableName = TaskEntityDao.TABLE_NAME)
data class TaskEntity(

        @PrimaryKey(autoGenerate = true)
        var id : Int,
        @ColumnInfo(name = "completed")
        val isComplete: Boolean,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "created_at")
        val createdAt: DateTime
)

