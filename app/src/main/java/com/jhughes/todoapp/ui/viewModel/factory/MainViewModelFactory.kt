package com.jhughes.todoapp.ui.viewModel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.net.ConnectivityManager
import com.jhughes.todoapp.data.manager.TasksManager
import com.jhughes.todoapp.ui.viewModel.MainViewModel

class MainViewModelFactory(
        private val application: Application,
        private val connectivityManager: ConnectivityManager,
        private val tasksManager: TasksManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, connectivityManager, tasksManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}