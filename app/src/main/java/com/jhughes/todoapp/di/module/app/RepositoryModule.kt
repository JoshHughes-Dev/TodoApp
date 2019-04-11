package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.local.dao.LiveDataTaskEntityDao
import com.jhughes.todoapp.data.local.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.local.repo.SimpleRoomTaskDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskLocalSource(database: AppDatabase): SimpleRoomTaskDataSource {
        return SimpleRoomTaskDataSource(database.taskDao(), TaskMapper())
    }

    @Provides
    @Singleton
    fun provideLiveDataTaskDao(database: AppDatabase): LiveDataTaskEntityDao {
        return database.liveDataTaskDao()
    }
}