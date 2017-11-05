package com.jhughes.todoapp

import android.app.Application
import com.jhughes.todoapp.injection.component.ApplicationComponent
import com.jhughes.todoapp.injection.component.DaggerApplicationComponent
import com.jhughes.todoapp.injection.module.ApplicationModule

class TodoApplication : Application() {

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)

    }


}