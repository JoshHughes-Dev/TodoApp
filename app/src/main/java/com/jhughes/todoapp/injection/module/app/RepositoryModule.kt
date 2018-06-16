package com.jhughes.todoapp.injection.module.app

import com.jhughes.todoapp.data.domain.repo.TaskRepository
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
    fun provideTaskRepository(database: AppDatabase, appExecutors: AppExecutors): TaskRepository {
        val taskDataSource = TaskDataSource(database.taskDao(), appExecutors, TaskMapper())
        return TaskRepository(taskDataSource)
    }
}