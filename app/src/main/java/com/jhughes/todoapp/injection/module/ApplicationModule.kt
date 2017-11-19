package com.jhughes.todoapp.injection.module

import android.app.Application
import android.app.NotificationManager
import android.arch.persistence.room.Room
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.manager.TasksManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideUiThread() : Handler {
        return Handler(Looper.getMainLooper())
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNotificationManager() : NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @Singleton
    fun provideAppDatabase() : AppDatabase {
        return Room.databaseBuilder(application.applicationContext,
                AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries() //temp until i use threads properly
                .build()
    }

    @Provides
    @Singleton
    fun provideTaskManager(database : AppDatabase) : TasksManager {
        return TasksManager(database, TaskMapper())
    }

}