package com.jhughes.todoapp.di.component.app

import android.app.Application
import android.content.Context
import com.jhughes.todoapp.TodoApplication
import dagger.BindsInstance
import dagger.android.AndroidInjector

interface BaseTodoApplicationComponent : AndroidInjector<TodoApplication> {

    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        @BindsInstance
        fun context(context: Context) : Builder
        fun build() : BaseTodoApplicationComponent
    }
}