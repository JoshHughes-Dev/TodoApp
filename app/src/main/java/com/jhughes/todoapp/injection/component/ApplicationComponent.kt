package com.jhughes.todoapp.injection.component

import android.app.NotificationManager
import android.net.ConnectivityManager
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app : TodoApplication)

    fun appExecutors() : AppExecutors

    fun connectivityManager(): ConnectivityManager

    fun notificationManager(): NotificationManager

    fun appDatabase() : AppDatabase

    fun tasksRepository() : TaskRepository
}

//AndroidInjectionModule::class,

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): ApplicationComponent
//    }