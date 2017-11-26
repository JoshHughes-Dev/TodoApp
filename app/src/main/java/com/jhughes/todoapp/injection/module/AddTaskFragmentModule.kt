package com.jhughes.todoapp.injection.module

import android.app.Activity
import android.content.Context
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AddTaskFragmentModule(private val activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideAddTaskViewModelFactory(): AddTaskViewModelFactory {
        return AddTaskViewModelFactory(activity.application)
    }
}