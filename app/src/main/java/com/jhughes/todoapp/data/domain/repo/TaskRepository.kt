package com.jhughes.todoapp.data.domain.repo

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.repo.TaskDataSource
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val localDataSource: TaskDataSource) {

    private val tasksMap = LinkedHashMap<String, Task>()

    init {
        localDataSource.getTasks { tasks ->
            refreshCache(tasks)
        }
    }

    fun getTasks(callback: (List<Task>) -> Unit) {

        if (tasksMap.isNotEmpty()) {
            callback(ArrayList(tasksMap.values))
        } else {
            localDataSource.getTasks { tasks ->
                refreshCache(tasks)
            }
        }
    }

    fun getTask(taskId: Int, callback: (Task?) -> Unit) {

        if (tasksMap.isNotEmpty()) {
            val id = taskId.toString()

            if (tasksMap.containsKey(id)) {
                callback(tasksMap[id])
            }
        } else {
            localDataSource.getTask(taskId) { task ->
                callback(task)
            }
        }
    }

    fun addTask(description: String, callback: (Task) -> Unit) {
        localDataSource.addTask(description) { task ->
            tasksMap[task.id.toString()] = task
            callback(task)
        }
    }

    fun completeTask(taskId: String) {
        if (tasksMap.containsKey(taskId)) {
            tasksMap[taskId]?.let { task ->
                task.isComplete = true
                tasksMap[taskId] = task

                localDataSource.completeTask(task)
            }
        }
    }

    fun activateTask(taskId: String) {
        if (tasksMap.containsKey(taskId)) {
            tasksMap[taskId]?.let { task ->
                task.isComplete = false
                tasksMap[taskId] = task

                localDataSource.activateTask(task)
            }
        }
    }

    fun clearTasks() {
        tasksMap.clear()
        localDataSource.clearTasks()
    }

    private fun refreshCache(tasks: List<Task>) {
        tasksMap.clear()

        for (task in tasks) {
            tasksMap[task.id.toString()] = task
        }
    }
}
