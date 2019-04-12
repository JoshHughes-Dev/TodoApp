package com.jhughes.todoapp.data.domain.repo

import android.util.Log
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

    fun completeTask(taskId: Int) {
        Log.d("Repo", "task complete")
        paperDbTasksDataSource.completeTask(taskId)
    }

    fun activateTask(taskId: Int) {
        Log.d("Repo", "task activated")
        paperDbTasksDataSource.activateTask(taskId)
    }

    fun addTask(description: String, callback: () -> Unit) {
        paperDbTasksDataSource.addTask(description)
        callback()
    }
}