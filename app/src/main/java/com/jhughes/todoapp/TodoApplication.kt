package com.jhughes.todoapp

import android.app.Application
import android.net.ConnectivityManager
import com.jhughes.todoapp.injection.component.ApplicationComponent
import com.jhughes.todoapp.injection.component.DaggerApplicationComponent
import com.jhughes.todoapp.injection.module.ApplicationModule
import javax.inject.Inject

class TodoApplication : Application() {

    lateinit var component : ApplicationComponent

    @Inject lateinit var connectivityManager: ConnectivityManager

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)
    }
}