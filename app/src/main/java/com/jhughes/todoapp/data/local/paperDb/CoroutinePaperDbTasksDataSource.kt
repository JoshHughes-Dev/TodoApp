package com.jhughes.todoapp.data.local.paperDb

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class CoroutinePaperDbTasksDataSource @Inject constructor() {

    private var cachedTasks : List<Task>? = null
    private var observableTasks : ObservableField<List<Task>> = ObservableField()

    suspend fun getTasks() : List<Task> = withContext(Dispatchers.IO) {
        cachedTasks ?: {
            val tasks = readTasks()
            cachedTasks = tasks
            tasks
        }.invoke()
    }

    fun getLiveTasks(scope : CoroutineScope) : LiveData<List<Task>> {

        val liveData = object : MutableLiveData<List<Task>>() {
            val propChangedCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    postValue((sender as? ObservableField<List<Task>>)?.get())
                }
            }

            override fun onActive() {
                super.onActive()
                observableTasks.addOnPropertyChangedCallback(propChangedCallback)
            }

            override fun onInactive() {
                super.onInactive()
                observableTasks.removeOnPropertyChangedCallback(propChangedCallback)
            }
        }

        scope.launch {
            withContext(Dispatchers.IO) {
                if (observableTasks.get() == null) {
                    observableTasks.set(readTasks())
                }
            }
        }

        return liveData
    }

    suspend fun getTask(taskId: Int) : Task? = withContext(Dispatchers.IO) {
        if (cachedTasks == null) {
           cachedTasks = readTasks()
        }

        cachedTasks?.find { it.id == taskId }
    }

    fun getLiveTask(scope : CoroutineScope, taskId: Int) : LiveData<Task> {
        val liveData = object : MutableLiveData<Task>() {

            val propChangedCallback = object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    val task = (sender as? ObservableField<List<Task>>)?.get()?.find { it.id == taskId }
                    postValue(task)
                }
            }

            override fun onActive() {
                super.onActive()
                observableTasks.addOnPropertyChangedCallback(propChangedCallback)
            }

            override fun onInactive() {
                super.onInactive()
                observableTasks.removeOnPropertyChangedCallback(propChangedCallback)
            }
        }

        scope.launch {
            withContext(Dispatchers.IO) {
                liveData.postValue(observableTasks.get()?.find { it.id == taskId })
            }
        }

        return liveData
    }

    suspend fun addTask(description : String) = withContext(Dispatchers.IO) {
        val tasks = (cachedTasks ?: readTasks()).toMutableList()

        val newId = (tasks.lastOrNull()?.id ?: 0) + 1
        val task = Task(newId, false, description, DateTime.now())

        tasks.add(task)

        saveTasks(tasks)
        cachedTasks = tasks
        observableTasks.set(tasks)
    }

    suspend fun completeTask(taskId : Int) = withContext(Dispatchers.IO) {
        val tasks = (cachedTasks ?: readTasks()).toMutableList()
        tasks.find { it.id == taskId }?.isComplete = true
        saveTasks(tasks)
        cachedTasks = tasks
        observableTasks.set(tasks)
    }

    suspend fun activateTask(taskId: Int) = withContext(Dispatchers.IO) {
        val tasks = readTasks().toMutableList()
        tasks.find { it.id == taskId }?.isComplete = false
        saveTasks(tasks)
        cachedTasks = tasks
        observableTasks.set(tasks)
    }

    suspend fun clearTasks() = withContext(Dispatchers.IO) {
        Paper.book().delete(ENTRY_NAME)
        cachedTasks = emptyList()
        observableTasks.set(emptyList())
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