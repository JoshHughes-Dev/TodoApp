package com.jhughes.todoapp.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "tasks")
data class RoomTask(

        @PrimaryKey
        var id : Int,
        @ColumnInfo(name = "is_complete")
        val isComplete: Boolean,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "created_at")
        val createdAt: DateTime
)

