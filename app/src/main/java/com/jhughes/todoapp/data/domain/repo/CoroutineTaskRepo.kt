package com.jhughes.todoapp.data.domain.repo

import com.jhughes.todoapp.data.Result
import com.jhughes.todoapp.data.domain.model.Task
import io.paperdb.Paper
import io.paperdb.PaperDbException
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoroutineTaskRepo @Inject constructor() {

    private var cachedTasks : List<Task>? = null

    suspend fun getTasks() : Result<List<Task>> {
        with(cachedTasks) {
            return if(this != null) {
                Result.Success(this)
            } else {
                try {
                    val tasks = readTasks()
                    cachedTasks = tasks
                    Result.Success(tasks)
                } catch (e : Exception) {
                    Result.Error(e)
                }
            }
        }

    }

    suspend fun addTask(description: String) {
        val tasks = readTasks().toMutableList()

        val id = (tasks.lastOrNull()?.id ?: 0) + 1
        val task = Task(id, false, description, DateTime.now())

        tasks.add(task)

        saveTasks(tasks)
        cachedTasks = tasks
    }

    suspend fun completeTask(taskId : Int) {
        val tasks = readTasks().toMutableList()
        tasks.find { it.id == taskId }?.isComplete = true
        saveTasks(tasks)
        cachedTasks = tasks
    }

    suspend fun activateTask(taskId : Int) {
        val tasks = readTasks().toMutableList()
        tasks.find { it.id == taskId }?.isComplete = false
        saveTasks(tasks)
        cachedTasks = tasks
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