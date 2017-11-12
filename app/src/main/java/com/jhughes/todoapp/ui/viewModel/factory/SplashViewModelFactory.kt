package com.jhughes.todoapp.ui.viewModel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Handler
import com.jhughes.todoapp.ui.viewModel.SplashViewModel

class SplashViewModelFactory(
        private val application: Application,
        private val uiThread: Handler) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(application, uiThread) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}