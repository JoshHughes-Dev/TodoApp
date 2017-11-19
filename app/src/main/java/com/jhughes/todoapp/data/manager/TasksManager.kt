package com.jhughes.todoapp.data.manager

import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper

class TasksManager(
        private val database : AppDatabase,
        private val mapper : TaskMapper) {

    fun getAllTasks() : MutableList<Task> {
        val roomTasks = database.taskDao().getAllTasks()
        val tasks = ArrayList<Task>()

        roomTasks.forEach {
            tasks.add(mapper.toDomain(it))
        }

        return tasks
    }

    fun addTask(task: Task) {
        database.taskDao().insertTask(mapper.toEntity(task))
    }

    fun clearTasks() {
        database.taskDao().deleteAll()
    }
}