package com.jhughes.todoapp.data.domain.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.dao.LiveDataTaskEntityDao
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.util.IoScheduler
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveDataTaskRepo @Inject constructor(
        private val taskEntityDao: LiveDataTaskEntityDao) {

    private val taskMapper: TaskMapper = TaskMapper()

    fun getTasks() : LiveData<List<Task>> {
        return Transformations.map(taskEntityDao.getAllTasks()) { taskEntities ->
            taskEntities.map { taskMapper.toDomain(it) }
        }
    }

    fun addTask(description : String, callback: (Task) -> Unit) {
        val task = Task(0, false, description, DateTime.now())

        IoScheduler.execute {
            taskEntityDao.insertTask(taskMapper.toEntity(task))
            IoScheduler.postToMainThread {
                callback(task)
            }
        }
    }

    fun completeTask(taskId: Int) {
        Log.d("Repo", "task complete")
        IoScheduler.execute {
            taskEntityDao.updateCompleted(taskId, true)
        }
    }

    fun activateTask(taskId: Int) {
        Log.d("Repo", "task activated")
        IoScheduler.execute {
            taskEntityDao.updateCompleted(taskId, false)
        }
    }
}