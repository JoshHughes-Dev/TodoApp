package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.util.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    SystemServiceModule::class,
    DataBaseModule::class,
    RepositoryModule::class
])
class DebugTodoApplicationModule  {

    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }
}