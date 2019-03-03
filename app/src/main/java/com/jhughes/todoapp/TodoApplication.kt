package com.jhughes.todoapp

import com.jhughes.todoapp.di.ComponentFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TodoApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return ComponentFactory.buildComponentFor(this)
    }
}