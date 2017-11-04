package com.jhughes.todoapp

import android.app.Application
import android.util.Log

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TodoApplication", "onCreate")
    }
}