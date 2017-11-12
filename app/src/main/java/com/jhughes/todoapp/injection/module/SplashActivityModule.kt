package com.jhughes.todoapp.injection.module

import android.os.Handler
import com.jhughes.todoapp.ui.SplashActivity
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule(private val activity: SplashActivity) {

    @Provides
    fun provideSplashViewModelFactory(uiThread: Handler):
            SplashViewModelFactory {
        return SplashViewModelFactory(activity.application, uiThread)
    }
}