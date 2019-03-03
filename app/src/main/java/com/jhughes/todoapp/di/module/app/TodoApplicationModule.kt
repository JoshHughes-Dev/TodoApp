package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.util.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [
    AndroidSupportInjectionModule::class,
    SystemServiceModule::class,
    DataBaseModule::class,
    RepositoryModule::class
])
class TodoApplicationModule {

    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }
}
