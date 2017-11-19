package com.jhughes.todoapp.injection.component

import android.app.NotificationManager
import android.net.ConnectivityManager
import android.os.Handler
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.manager.TasksManager
import com.jhughes.todoapp.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
public interface ApplicationComponent {

    fun inject(app : TodoApplication)

    fun uiThread(): Handler

    fun connectivityManager(): ConnectivityManager

    fun notifcationManager(): NotificationManager

    fun appDatabase() : AppDatabase

    fun tasksManager() : TasksManager
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