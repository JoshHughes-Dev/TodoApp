package com.jhughes.todoapp.injection.module.activity

import android.app.Application
import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.injection.scope.ActivityScope
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import dagger.Module
import dagger.Provides

//module doesnt contain any abstract android injectors, doesnt need to be abstract

@Module(includes = [BaseActivityModule::class])
class SplashActivityModule {

    @Provides
    @ActivityScope
    fun provideSplashViewModelFactory(application: Application, appExecutors: AppExecutors): SplashViewModelFactory {
        return SplashViewModelFactory(application, appExecutors)
    }
}