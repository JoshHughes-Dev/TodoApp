package com.jhughes.todoapp

import android.app.Application
import android.net.ConnectivityManager
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.injection.component.ApplicationComponent
import com.jhughes.todoapp.injection.component.DaggerApplicationComponent
import com.jhughes.todoapp.injection.module.ApplicationModule
import org.joda.time.DateTime
import javax.inject.Inject

class TodoApplication : Application() {

    lateinit var component : ApplicationComponent

    @Inject lateinit var connectivityManager: ConnectivityManager
    @Inject lateinit var taskRepo : TaskRepository

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)

        setUpMockData()
    }

    private fun setUpMockData() {
        taskRepo.clearTasks()

        taskRepo.addTask(Task(1, true, "task 1", DateTime.now()))
        taskRepo.addTask(Task(2, false, "task 2", DateTime.now()))
        taskRepo.addTask(Task(3, false, "task 3", DateTime.now()))
    }
}