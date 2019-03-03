package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.local.repo.TaskDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskLocalSource(database: AppDatabase): TaskDataSource {
        return TaskDataSource(database.taskDao(), TaskMapper())
    }
}