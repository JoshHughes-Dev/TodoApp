package com.jhughes.todoapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject


class MainViewModel @Inject constructor(
        val taskRepository: TaskRepository) : ArchViewModel() {

    val tasks : MutableLiveData<List<Task>> = MutableLiveData()

    init {
        refreshData()
    }

    fun fabClick() {
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