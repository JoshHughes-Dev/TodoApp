package com.jhughes.todoapp.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.jhughes.todoapp.data.local.dao.TaskEntityDao
import org.joda.time.DateTime

@Entity(tableName = TaskEntityDao.TABLE_NAME)
data class TaskEntity(

        @PrimaryKey
        var id : Int,
        @ColumnInfo(name = "is_complete")
        val isComplete: Boolean,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "created_at")
        val createdAt: DateTime
)

