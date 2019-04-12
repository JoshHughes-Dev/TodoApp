package com.jhughes.todoapp.ui.viewModel.tasks

import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class SimpleTasksViewModel @Inject constructor(
        val taskRepository: TaskRepository) : TasksViewModel() {

    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        refreshData()
    }

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    fun refreshData() {
        taskRepository.getTasks {
            this.tasks.value = it
        }
    }

    class Nav {
        object AddNewTask : NavigationRequest()
    }
}