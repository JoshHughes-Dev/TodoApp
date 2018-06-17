package com.jhughes.todoapp.injection.component.app

import android.app.Application
import com.jhughes.todoapp.TodoApplication
import dagger.BindsInstance
import dagger.android.AndroidInjector

interface BaseTodoApplicationComponent : AndroidInjector<TodoApplication> {

    interface Builder {
        @BindsInstance
        fun application(application: Application): BaseTodoApplicationComponent.Builder
        fun build(): BaseTodoApplicationComponent
    }
}