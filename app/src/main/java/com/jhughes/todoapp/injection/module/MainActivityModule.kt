package com.jhughes.todoapp.injection.module

import android.app.ProgressDialog
import android.content.Context
import com.jhughes.todoapp.ui.MainActivity
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideProgressDialog(): ProgressDialog {
        val progress = ProgressDialog(activity)
        progress.setMessage("loading")
        return progress
    }


    @Provides
    fun provideMainViewModelFactory():
            MainViewModelFactory {
        return MainViewModelFactory(activity.application)
    }
}