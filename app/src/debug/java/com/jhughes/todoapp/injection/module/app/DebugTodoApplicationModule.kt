package com.jhughes.todoapp.injection.module.app

import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.injection.scopedItems.DebugSingletonItem
import com.jhughes.todoapp.injection.scopedItems.SingletonItem
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
    fun provideSingletonItem(): SingletonItem {
        return DebugSingletonItem()
    }

    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }
}