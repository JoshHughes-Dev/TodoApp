package com.jhughes.todoapp.ui.viewModel.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jhughes.todoapp.ui.viewModel.AddTaskViewModel

class AddTaskViewModelFactory(private val application: Application)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            return AddTaskViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}