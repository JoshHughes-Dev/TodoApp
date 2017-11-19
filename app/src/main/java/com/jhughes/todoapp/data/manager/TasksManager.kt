package com.jhughes.todoapp.data.manager

import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper

class TasksManager(val database : AppDatabase, val mapper: TaskMapper) {

    fun getAllTasks() : MutableList<Task> {
        var roomTasks = database.taskDao().getAllTasks()
        var tasks = ArrayList<Task>()

        roomTasks.forEach {
            tasks.add(mapper.toDomain(it))
        }

        return tasks
    }

    fun addTask(task: Task) {
        val roomTask = mapper.toLocal(task)

        database.taskDao().insertTask(roomTask)
    }
}