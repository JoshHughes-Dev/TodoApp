package com.jhughes.todoapp.data.local.paperDb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jhughes.todoapp.data.domain.model.Task
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuperPaperDbTasksDataSource @Inject constructor() {

    private val cachedTasks : MutableLiveData<List<Task>> = MutableLiveData()

    fun getTasks(scope : CoroutineScope) : LiveData<List<Task>> {
        if (cachedTasks.value == null) {
            scope.launch {
                withContext(Dispatchers.IO) {
                    cachedTasks.postValue(readTasks())
                }
            }
        }
        return cachedTasks
    }

    fun getTask(taskId: Int) : LiveData<Task?> {
        return Transformations.map(cachedTasks) { tasks ->
            tasks.find { it.id == taskId }
        }
    }

    suspend fun addTask(description : String) = withContext(Dispatchers.IO) {
        val tasks = (cachedTasks.value ?: readTasks()).toMutableList()

        val newId = (tasks.lastOrNull()?.id ?: 0) + 1
        val task = Task(newId, false, description, DateTime.now())

        tasks.add(task)

        saveTasks(tasks)
        cachedTasks.postValue(tasks)
    }

    suspend fun completeTask(taskId : Int) = withContext(Dispatchers.IO) {
        val tasks = (cachedTasks.value ?: readTasks()).toMutableList()
        tasks.find { it.id == taskId }?.isComplete = true
        saveTasks(tasks)
        cachedTasks.postValue(tasks)
    }

    suspend fun activateTask(taskId: Int) = withContext(Dispatchers.IO) {
        val tasks = readTasks().toMutableList()
        tasks.find { it.id == taskId }?.isComplete = false
        saveTasks(tasks)
        cachedTasks.postValue(tasks)
    }

    suspend fun clearTasks() = withContext(Dispatchers.IO) {
        Paper.book().delete(ENTRY_NAME)
        cachedTasks.postValue(emptyList())
    }

    private fun readTasks() : List<Task> {
        return Paper.book().read(ENTRY_NAME, emptyList())
    }

    private fun saveTasks(tasks : List<Task>) {
        Paper.book().write(ENTRY_NAME, tasks)
    }

    companion object {
        private const val ENTRY_NAME = "tasks"
    }
}