package com.jhughes.todoapp.di.module.app

import com.jhughes.todoapp.data.local.room.dao.LiveDataTaskEntityDao
import com.jhughes.todoapp.data.local.room.db.AppDatabase
import com.jhughes.todoapp.data.local.mapper.TaskMapper
import com.jhughes.todoapp.data.local.room.SimpleRoomTaskDataSource
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