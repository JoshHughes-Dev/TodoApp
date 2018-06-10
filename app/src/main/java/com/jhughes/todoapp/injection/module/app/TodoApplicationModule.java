package com.jhughes.todoapp.injection.module.app;

import android.app.Application;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;

import com.jhughes.todoapp.data.domain.repo.TaskRepository;
import com.jhughes.todoapp.data.local.db.AppDatabase;
import com.jhughes.todoapp.data.local.mapper.TaskMapper;
import com.jhughes.todoapp.data.local.repo.TaskDataSource;
import com.jhughes.todoapp.data.util.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TodoApplicationModule {

    @Provides
    @Singleton
    public AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Application application) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public NotificationManager provideNotificationManager(Application application) {
        return (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries() //temp until i use threads properly
                .build();
    }

    @Provides
    @Singleton
    public TaskRepository provideTaskRepository(AppDatabase database, AppExecutors appExecutors) {
        TaskDataSource taskDataSource = new TaskDataSource(database.taskDao(), appExecutors, new TaskMapper());
        return new TaskRepository(taskDataSource);
    }
}
