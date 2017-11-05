package com.jhughes.todoapp.injection.module

import android.content.Context
import com.jhughes.todoapp.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }
}