package com.jhughes.todoapp.data.domain.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jhughes.todoapp.data.SimpleResult
import com.jhughes.todoapp.data.Resource
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.paperDb.SuperPaperDbTasksDataSource
import com.jhughes.todoapp.safeAction
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuperTasksRepo @Inject constructor(
        private val localDataSource : SuperPaperDbTasksDataSource) {

    fun getTasks(scope : CoroutineScope) : LiveData<Resource<List<Task>>> {
        val data = MediatorLiveData<Resource<List<Task>>>().apply {
            value = Resource.Loading
        }

        try {
            data.addSource(localDataSource.getTasks(scope)) {
                data.value = Resource.Success(it)
            }
        } catch (e : Exception) {
            data.value = Resource.Error(e)
        }

        return data
    }

    suspend fun addTask(description: String) : SimpleResult {
        return safeAction { localDataSource.addTask(description) }
    }

    suspend fun completeTask(taskId : Int) : SimpleResult {
        return safeAction { localDataSource.completeTask(taskId) }
    }

    suspend fun activateTask(taskId : Int) : SimpleResult {
        return safeAction { localDataSource.activateTask(taskId) }
    }

    suspend fun clearTasks() {
        localDataSource.clearTasks()
    }

}