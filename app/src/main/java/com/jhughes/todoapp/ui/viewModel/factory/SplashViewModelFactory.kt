package com.jhughes.todoapp.ui.viewModel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.ui.viewModel.SplashViewModel

class SplashViewModelFactory(
        private val application: Application,
        private val appExecutors: AppExecutors) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(application, appExecutors) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}