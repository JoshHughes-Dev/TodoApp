package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.net.ConnectivityManager
import android.widget.Toast
import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.data.manager.TasksManager
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import org.joda.time.DateTime
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application,
        private val connectivityManager: ConnectivityManager,
        private val tasksManager: TasksManager) : BaseViewModel(application) {

    private val adapter = TaskAdapter(application)

    init {
        adapter.addTasks(tasksManager.getAllTasks())
    }

    @Bindable
    fun getAdapter(): TaskAdapter {
        return adapter
    }

    fun fabClick() {
        Toast.makeText(context, "fab clicked", Toast.LENGTH_LONG).show()

        val task = Task(adapter.itemCount + 1, false, "new task", DateTime.now())

        tasksManager.addTask(task)
        adapter.addTask(task)
    }
}