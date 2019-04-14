package com.jhughes.todoapp.data.local.paperDb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.util.IoScheduler
import io.paperdb.Paper
import io.paperdb.PaperDbException
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PaperDbTasksDataSource @Inject constructor() {

    private val cachedTasks : MutableLiveData<List<Task>> = MutableLiveData()

    fun getTasks() : LiveData<List<Task>>{
        if (cachedTasks.value == null) {
            IoScheduler.execute {
                cachedTasks.postValue(readTasks())
            }
        }
        return cachedTasks
    }

    fun getTask(taskId: Int) : LiveData<Task> {
        return Transformations.map(getTasks()) { tasks ->
            tasks.find { it.id == taskId }
        }
    }

    fun addTask(description : String) {
        IoScheduler.execute {
            val tasks = readTasks().toMutableList()

            val id = (tasks.lastOrNull()?.id ?: 0) + 1
            val task = Task(id, false, description, DateTime.now())

            tasks.add(task)

            saveTasks(tasks)
            cachedTasks.postValue(tasks)
        }
    }

    fun completeTask(taskId : Int) {
        IoScheduler.execute {
            val tasks = readTasks().toMutableList()
            tasks.find { it.id == taskId }?.isComplete = true
            saveTasks(tasks)
            cachedTasks.postValue(tasks)
        }
    }

    fun activateTask(taskId: Int) {
        IoScheduler.execute {
            val tasks = readTasks().toMutableList()
            tasks.find { it.id == taskId }?.isComplete = false
            saveTasks(tasks)
            cachedTasks.postValue(tasks)
        }
    }

    fun clearTasks() {
        IoScheduler.execute {
            deleteTasks()
            cachedTasks.postValue(emptyList())
        }
    }

    private fun deleteTasks() {
        try {
            Paper.book().delete(ENTRY_NAME)
        } catch (e : PaperDbException) {
            //todo error
        }
    }

    private fun readTasks() : List<Task> {
        return try {
            Paper.book().read(ENTRY_NAME, emptyList())
        } catch (e : PaperDbException) {
            //todo error
            emptyList()
        }
    }

    private fun saveTasks(tasks : List<Task>) {
        try {
            Paper.book().write(ENTRY_NAME, tasks)
        } catch (e : PaperDbException) {
            //todo error
        }

    }

    companion object {
        private const val ENTRY_NAME = "tasks"
    }
}