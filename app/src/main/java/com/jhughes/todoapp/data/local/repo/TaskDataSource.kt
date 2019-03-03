package com.jhughes.todoapp.data.local.repo

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.dao.TaskEntityDao
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.util.IoScheduler
import org.joda.time.DateTime

class TaskDataSource(
        private val taskEntityDao: TaskEntityDao,
        private val taskMapper: TaskMapper) {

    fun getTasks(callback: (List<Task>) -> Unit) {
        IoScheduler.execute {
            val taskEntities = taskEntityDao.getAllTasks()
            val tasks = ArrayList<Task>()

            taskEntities.forEach {
                tasks.add(taskMapper.toDomain(it))
            }

            IoScheduler.postToMainThread {
                callback(tasks)
            }
        }
    }

    fun getTask(taskId: Int, callback: (Task) -> Unit) {
        IoScheduler.execute {
            val taskEntity = taskEntityDao.findTaskById(taskId)
            val task = taskMapper.toDomain(taskEntity)

            IoScheduler.postToMainThread {
                callback(task)
            }
        }
    }

    fun addTask(description : String, callback: (Task) -> Unit) {
        val task = Task(0, false, description, DateTime.now())

        IoScheduler.execute {
            val id = taskEntityDao.insertTask(taskMapper.toEntity(task))
            val taskEntity = taskEntityDao.findTaskById(id.toInt())
            IoScheduler.postToMainThread {
                callback(taskMapper.toDomain(taskEntity))
            }
        }
    }

    fun completeTask(task: Task) {
        changeTaskStatus(task, true)
    }

    fun activateTask(task: Task) {
        changeTaskStatus(task, false)
    }

    fun clearTasks() {
        IoScheduler.execute {
            taskEntityDao.deleteAll()
        }
    }

    private fun changeTaskStatus(task: Task, status: Boolean) {
        val taskEntity = taskMapper.toEntity(task)

        IoScheduler.execute {
            taskEntityDao.updateCompleted(taskEntity.id, status)
        }
    }
}