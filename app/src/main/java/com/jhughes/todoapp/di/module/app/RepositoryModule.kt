package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.local.repo.TaskDataSource
import com.jhughes.todoapp.data.util.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskLocalSource(database: AppDatabase, appExecutors: AppExecutors): TaskDataSource {
        return TaskDataSource(database.taskDao(), appExecutors, TaskMapper())
    }
}