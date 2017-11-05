package com.jhughes.todoapp.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
public class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Context {
        return application
    }
}