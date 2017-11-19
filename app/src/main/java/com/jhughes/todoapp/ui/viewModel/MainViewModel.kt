package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.net.ConnectivityManager
import android.widget.Toast
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import org.joda.time.DateTime
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application,
        private val connectivityManager: ConnectivityManager,
        private val taskRepository: TaskRepository) : BaseViewModel(application) {

    private val adapter = TaskAdapter(application)

    init {
        taskRepository.getTasks(TaskRepository.GetTasksCallback {
            tasks ->  adapter.addTasks(tasks)
        })
    }

    @Bindable
    fun getAdapter(): TaskAdapter {
        return adapter
    }

    fun fabClick() {
        Toast.makeText(context, "fab clicked", Toast.LENGTH_LONG).show()

        val task = Task(adapter.itemCount + 1, false, "new task", DateTime.now())

        taskRepository.addTask(task)
        adapter.addTask(task)
    }
}