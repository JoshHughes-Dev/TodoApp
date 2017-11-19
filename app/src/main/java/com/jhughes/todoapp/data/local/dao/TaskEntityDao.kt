package com.jhughes.todoapp.data.local.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jhughes.todoapp.data.local.entity.TaskEntity


@Dao
interface TaskEntityDao {

    companion object {
        const val TABLE_NAME = "tasks"
    }

    @Query("select * from $TABLE_NAME")
    fun getAllTasks(): List<TaskEntity>

    @Query("select * from $TABLE_NAME where id = :id")
    fun findTaskById(id: Int): TaskEntity

    @Insert(onConflict = REPLACE)
    fun insertTask(taskEntity: TaskEntity)

    @Update(onConflict = REPLACE)
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("delete from $TABLE_NAME")
    fun deleteAll()
}