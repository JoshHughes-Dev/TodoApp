package com.jhughes.todoapp.di.module.activity

import com.jhughes.todoapp.di.scope.ActivityScope
import com.jhughes.todoapp.ui.activity.ChooserActivity
import com.jhughes.todoapp.ui.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [TasksBindingModule::class])
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivity() : SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ChooserActivityModule::class])
    abstract fun chooserActivity() : ChooserActivity

}

