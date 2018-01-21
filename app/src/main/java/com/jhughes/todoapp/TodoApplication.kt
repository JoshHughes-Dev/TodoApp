package com.jhughes.todoapp

import android.app.Application
import android.net.ConnectivityManager
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.injection.component.ApplicationComponent
import com.jhughes.todoapp.injection.component.DaggerApplicationComponent
import com.jhughes.todoapp.injection.module.ApplicationModule
import javax.inject.Inject

class TodoApplication : Application() {

    public lateinit var component : ApplicationComponent

    @Inject lateinit var connectivityManager: ConnectivityManager
    @Inject lateinit var taskRepo : TaskRepository

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)

        //setUpMockData()
    }


    private fun setUpMockData() {

        taskRepo.clearTasks()

//        taskRepo.addTask(Task(1, true, "task 1", DateTime.now()))
//        taskRepo.addTask(Task(2, false, "task 2", DateTime.now()))
//        taskRepo.addTask(Task(3, false, "task 3", DateTime.now()))
//        taskRepo.addTask(Task(4, false, "task 4", DateTime.now()))
//        taskRepo.addTask(Task(5, false, "task 5", DateTime.now()))
    }
}