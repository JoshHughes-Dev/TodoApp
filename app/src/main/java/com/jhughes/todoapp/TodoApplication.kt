package com.jhughes.todoapp

import com.jhughes.todoapp.di.ComponentFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.danlew.android.joda.JodaTimeAndroid

class TodoApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return ComponentFactory.buildComponentFor(this)
    }
}