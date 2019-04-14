package com.jhughes.todoapp.data.domain.repo

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.paperDb.PaperDbTasksDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaperDbTaskRepo @Inject constructor(
        private val paperDbTasksDataSource: PaperDbTasksDataSource) {

    fun getTasks() : LiveData<List<Task>> {
        return paperDbTasksDataSource.getTasks()
    }

    fun getTask(taskId: Int) : LiveData<Task> {
        return paperDbTasksDataSource.getTask(taskId)
    }

    fun addTask(description: String, callback: () -> Unit) {
        paperDbTasksDataSource.addTask(description)
        callback()
    }

    fun completeTask(taskId: Int) {
        paperDbTasksDataSource.completeTask(taskId)
    }

    fun activateTask(taskId: Int) {
        paperDbTasksDataSource.activateTask(taskId)
    }

    fun clearTasks() {
        paperDbTasksDataSource.clearTasks()
    }
}