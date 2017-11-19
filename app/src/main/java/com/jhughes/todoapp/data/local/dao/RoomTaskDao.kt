package com.jhughes.todoapp.data.local.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jhughes.todoapp.data.local.entity.RoomTask


@Dao
interface RoomTaskDao {

    @Query("select * from tasks")
    fun getAllTasks(): List<RoomTask>

    @Query("select * from tasks where id = :id")
    fun findTaskById(id: Int): RoomTask

    @Insert(onConflict = REPLACE)
    fun insertTask(task: RoomTask)

    @Update(onConflict = REPLACE)
    fun updateTask(task: RoomTask)

    @Delete
    fun deleteTask(task: RoomTask)
}