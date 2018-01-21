package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.net.ConnectivityManager
import com.jhughes.todoapp.SingleLiveEvent
import com.jhughes.todoapp.data.Navigator
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application,
        private val connectivityManager: ConnectivityManager,
        private val taskRepository: TaskRepository) : BaseViewModel(application) {

    private val adapter = TaskAdapter(application, taskRepository)

    val navigationEvent = SingleLiveEvent<Navigator>()

    init {
        setTasks()
    }

    @Bindable
    fun getAdapter(): TaskAdapter {
        return adapter
    }

    fun fabClick() {
        navigationEvent.call()
    }

    fun setTasks() {
        taskRepository.getTasks(TaskRepository.GetTasksCallback {
            tasks ->  adapter.addTasks(tasks)
        })
    }
}