package com.jhughes.todoapp.injection.module.app

import android.app.Application
import android.arch.persistence.room.Room
import com.jhughes.todoapp.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext,
                AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries() //temp until i use threads properly
                .build()
    }
}