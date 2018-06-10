package com.jhughes.todoapp

import android.app.Activity
import android.app.Application
import android.net.ConnectivityManager
import com.jhughes.todoapp.injection.component.app.DaggerTodoApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TodoApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var connectivityManager: ConnectivityManager

    override fun onCreate() {
        super.onCreate()
        DaggerTodoApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}