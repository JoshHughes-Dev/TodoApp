package com.jhughes.todoapp.di.module.activity

import androidx.lifecycle.ViewModel
import com.jhughes.todoapp.di.module.app.ViewModelKey
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//module doesnt contain any abstract android injectors, doesnt need to be abstract

@Module
abstract class SplashActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel) : ViewModel
}