package com.jhughes.todoapp.data.local.repo

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.data.local.dao.TaskEntityDao
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.util.AppExecutors

class TaskDataSource(
        private val taskEntityDao: TaskEntityDao,
        private val appExecutors: AppExecutors,
        private val taskMapper: TaskMapper) {

    fun getTasks(callback: TaskRepository.GetTasksCallback) {
        val runnable = Runnable {
            val taskEntities = taskEntityDao.getAllTasks()
            val tasks = ArrayList<Task>()

            taskEntities.forEach {
                tasks.add(taskMapper.toDomain(it))
            }

            appExecutors.mainThread().execute({
                callback.onComplete(tasks)
            })
        }

        appExecutors.diskIO().execute(runnable)
    }

    fun getTask(taskId: Int, callback: TaskRepository.GetTaskCallback) {
        val runnable = Runnable {
            val taskEntity = taskEntityDao.findTaskById(taskId)
            val task = taskMapper.toDomain(taskEntity)

            appExecutors.mainThread().execute({
                callback.onComplete(task)
            })
        }

        appExecutors.diskIO().execute(runnable)
    }

    fun saveTask(task: Task) {
        val runnable = Runnable {
            taskEntityDao.insertTask(taskMapper.toEntity(task))
        }

        appExecutors.diskIO().execute(runnable)
    }

    fun completeTask(task: Task) {
        changeTaskStatus(task, true)
    }

    fun activateTask(task: Task) {
        changeTaskStatus(task, false)
    }

    fun clearTasks() {

        val runnable = Runnable {
            taskEntityDao.deleteAll()
        }

        appExecutors.diskIO().execute(runnable)
    }

    private fun changeTaskStatus(task: Task, status: Boolean) {
        val taskEntity = taskMapper.toEntity(task)

        val runnable = Runnable {
            taskEntityDao.updateCompleted(taskEntity.id, false)
        }

        appExecutors.diskIO().execute(runnable)
    }
}