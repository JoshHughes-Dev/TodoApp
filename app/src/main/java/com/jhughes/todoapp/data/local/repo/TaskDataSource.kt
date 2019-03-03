package com.jhughes.todoapp.data.local.repo

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.dao.TaskEntityDao
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.util.AppExecutors
import org.joda.time.DateTime

class TaskDataSource(
        private val taskEntityDao: TaskEntityDao,
        private val appExecutors: AppExecutors,
        private val taskMapper: TaskMapper) {

    fun getTasks(callback: (List<Task>) -> Unit) {
        appExecutors.diskIO().execute {
            val taskEntities = taskEntityDao.getAllTasks()
            val tasks = ArrayList<Task>()

            taskEntities.forEach {
                tasks.add(taskMapper.toDomain(it))
            }

            appExecutors.mainThread().execute{
                callback(tasks)
            }
        }
    }

    fun getTask(taskId: Int, callback: (Task) -> Unit) {
        appExecutors.diskIO().execute {
            val taskEntity = taskEntityDao.findTaskById(taskId)
            val task = taskMapper.toDomain(taskEntity)

            appExecutors.mainThread().execute{
                callback(task)
            }
        }
    }

    fun addTask(description : String, callback: (Task) -> Unit) {
        val task = Task(0, false, description, DateTime.now())

        appExecutors.diskIO().execute {
            val id = taskEntityDao.insertTask(taskMapper.toEntity(task))
            val taskEntity = taskEntityDao.findTaskById(id.toInt())
            callback(taskMapper.toDomain(taskEntity))
        }
    }

    fun completeTask(task: Task) {
        changeTaskStatus(task, true)
    }

    fun activateTask(task: Task) {
        changeTaskStatus(task, false)
    }

    fun clearTasks() {
        appExecutors.diskIO().execute {
            taskEntityDao.deleteAll()
        }
    }

    private fun changeTaskStatus(task: Task, status: Boolean) {
        val taskEntity = taskMapper.toEntity(task)

        appExecutors.diskIO().execute {
            taskEntityDao.updateCompleted(taskEntity.id, status)
        }
    }
}