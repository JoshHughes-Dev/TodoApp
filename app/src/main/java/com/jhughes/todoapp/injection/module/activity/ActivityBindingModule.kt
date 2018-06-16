package com.jhughes.todoapp.injection.module.activity

import com.jhughes.todoapp.injection.scope.ActivityScope
import com.jhughes.todoapp.ui.MainActivity
import com.jhughes.todoapp.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivity() : SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity() : MainActivity
}