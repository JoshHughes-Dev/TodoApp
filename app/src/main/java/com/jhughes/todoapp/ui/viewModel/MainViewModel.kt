package com.jhughes.todoapp.ui.viewModel

import android.arch.lifecycle.ViewModel
import android.util.Log

class MainViewModel : ViewModel() {


    public fun fabClick() {
        Log.d("MainViewModel", "on fab click")
    }
}