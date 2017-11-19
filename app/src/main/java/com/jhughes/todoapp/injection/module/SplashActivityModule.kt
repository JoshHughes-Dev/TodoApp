package com.jhughes.todoapp.injection.module

import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.ui.SplashActivity
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule(private val activity: SplashActivity) {

    @Provides
    fun provideSplashViewModelFactory(appExecutors: AppExecutors):
            SplashViewModelFactory {
        return SplashViewModelFactory(activity.application, appExecutors)
    }
}