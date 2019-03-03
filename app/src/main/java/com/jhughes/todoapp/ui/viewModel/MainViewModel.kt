package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.net.ConnectivityManager
import androidx.databinding.Bindable
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import javax.inject.Inject


class MainViewModel @Inject constructor(
        application: Application,
        private val connectivityManager: ConnectivityManager,
        private val taskRepository: TaskRepository) : ArchViewModel() {

    private val adapter = TaskAdapter(taskRepository)


    init {
        setTasks()
    }

    @Bindable
    fun getAdapter(): TaskAdapter {
        return adapter
    }

    fun fabClick() {
//        navigationEvent.call()
    }

    fun setTasks() {
        taskRepository.getTasks {
            tasks ->  adapter.addTasks(tasks)
        }
    }
}