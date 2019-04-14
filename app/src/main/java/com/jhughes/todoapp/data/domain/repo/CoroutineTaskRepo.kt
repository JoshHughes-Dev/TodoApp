package com.jhughes.todoapp.data.domain.repo

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.Result
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.paperDb.CoroutinePaperDbTasksDataSource
import com.jhughes.todoapp.safeActionResult
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoroutineTaskRepo @Inject constructor(
        private val localDataSource : CoroutinePaperDbTasksDataSource) {

    suspend fun getTasks() : Result<List<Task>> {
        return safeActionResult { localDataSource.getTasks() }
    }

    fun getLiveTasks(scope: CoroutineScope) : LiveData<List<Task>> {
        return localDataSource.getLiveTasks(scope)
    }

    suspend fun addTask(description: String) {
        localDataSource.addTask(description)
    }

    suspend fun completeTask(taskId : Int) {
        localDataSource.completeTask(taskId)
    }

    suspend fun activateTask(taskId : Int) {
        localDataSource.activateTask(taskId)
    }

    suspend fun clearTasks() {
        localDataSource.clearTasks()
    }
}