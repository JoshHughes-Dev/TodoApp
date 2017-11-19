package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.net.ConnectivityManager
import android.widget.Toast
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application,
        private val connectivityManager: ConnectivityManager) : BaseViewModel(application) {

    private val adapter = TaskAdapter(application)

    @Bindable
    fun getAdapter(): TaskAdapter {
        return adapter
    }

    fun fabClick() {
        Toast.makeText(context, "fab clicked", Toast.LENGTH_LONG).show()
    }
}