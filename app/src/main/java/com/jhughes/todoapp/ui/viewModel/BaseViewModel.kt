package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context

open class BaseViewModel(application : Application) : AndroidViewModel(application) {

    val context : Context
        get() = getApplication()

}