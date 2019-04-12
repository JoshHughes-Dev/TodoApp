package com.jhughes.todoapp.data.domain.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.SimpleResult
import com.jhughes.todoapp.data.State
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
class SuperTasksRepo @Inject constructor() {

    private val cachedTasks : MutableLiveData<State<List<Task>>> = MutableLiveData()

    fun getTasks(scope : CoroutineScope) : LiveData<State<List<Task>>> {
        if (cachedTasks.value == null) {
            cachedTasks.value = State.Loading
            scope.launch {
                try {
                    val tasks = withContext(Dispatchers.IO) {
                        readTasks()
                    }
                    cachedTasks.postValue(State.Success(tasks))
                } catch (e : Exception) {
                    cachedTasks.postValue(State.Error(e))
                }
            }
        }

        return cachedTasks
    }

    suspend fun addTask(description: String) : SimpleResult {
        return safeAction {
            val tasks = readTasks().toMutableList()

            val id = (tasks.lastOrNull()?.id ?: 0) + 1
            val task = Task(id, false, description, DateTime.now())

            tasks.add(task)

            saveTasks(tasks)
            cachedTasks.postValue(State.Success(tasks))
        }
    }

    suspend fun completeTask(taskId : Int) : SimpleResult {
        return safeAction {
            val tasks = readTasks().toMutableList()
            tasks.find { it.id == taskId }?.isComplete = true
            saveTasks(tasks)
            cachedTasks.postValue(State.Success(tasks))
        }
    }

    suspend fun activateTask(taskId : Int) : SimpleResult {
        return safeAction {
            val tasks = readTasks().toMutableList()
            tasks.find { it.id == taskId }?.isComplete = false
            saveTasks(tasks)
            cachedTasks.postValue(State.Success(tasks))
        }
    }

    private fun safeAction(func : () -> Unit) : SimpleResult {
        return try {
            func()
            SimpleResult.Success
        } catch (e : Exception) {
            SimpleResult.Error(e)
        }
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