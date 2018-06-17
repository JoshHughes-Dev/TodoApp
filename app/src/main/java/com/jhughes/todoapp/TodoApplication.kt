package com.jhughes.todoapp

import com.jhughes.todoapp.injection.ComponentFactory
import com.jhughes.todoapp.injection.scopedItems.SingletonItem
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class TodoApplication : DaggerApplication() {

    @Inject lateinit var singletonItem: SingletonItem

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return ComponentFactory.buildComponentFor(this)
    }
}