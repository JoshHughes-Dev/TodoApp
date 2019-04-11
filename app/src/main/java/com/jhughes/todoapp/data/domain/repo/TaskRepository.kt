package com.jhughes.todoapp.data.domain.repo

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.repo.TaskDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val localDataSource: TaskDataSource) {

    private var cachedTasks : List<Task>? = null

    fun getTasks(callback: (List<Task>) -> Unit) {
        with(cachedTasks) {
            if(this != null) {
                callback(this)
            } else {
                localDataSource.getTasks { tasks ->
                    cachedTasks = tasks
                    callback(tasks)
                }
            }
        }
    }

    fun getTask(taskId: Int, callback: (Task?) -> Unit) {
        with(cachedTasks) {
            if (this != null) {
                val task = find { it.id == taskId }
                callback(task)
            } else {
                getTasks { tasks ->
                    val task = tasks.find { it.id == taskId }
                    callback(task)
                }
            }
        }
    }

    fun addTask(description: String, callback: (Task) -> Unit) {
        localDataSource.addTask(description) { task ->
            cachedTasks = cachedTasks?.toMutableList()?.apply {
                add(task)
            }
            callback(task)
        }
    }

    fun completeTask(taskId: Int) {
        cachedTasks?.find { it.id == taskId }?.let {
            it.isComplete = true
            localDataSource.completeTask(it)
        }
    }

    fun activateTask(taskId: Int) {
        cachedTasks?.find { it.id == taskId }?.let {
            it.isComplete = false
            localDataSource.activateTask(it)
        }
    }

    fun clearTasks() {
        cachedTasks = null
        localDataSource.clearTasks()
    }
}
