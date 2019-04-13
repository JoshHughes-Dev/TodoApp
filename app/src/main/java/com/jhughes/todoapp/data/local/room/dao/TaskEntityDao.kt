package com.jhughes.todoapp.data.local.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
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
    fun insertTask(taskEntity: TaskEntity) : Long

    @Update(onConflict = REPLACE)
    fun updateTask(taskEntity: TaskEntity)

    @Query("UPDATE $TABLE_NAME SET completed = :completed WHERE id = :taskId")
    fun updateCompleted(taskId: Int, completed: Boolean)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("delete from $TABLE_NAME")
    fun deleteAll()
}