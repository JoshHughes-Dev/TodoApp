package com.jhughes.todoapp.ui.viewModel.tasks

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.LiveDataTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import javax.inject.Inject

class LiveDataTasksViewModel @Inject constructor(
        val liveDataTaskRepo: LiveDataTaskRepo) : TasksViewModel() {

    val tasks: LiveData<List<Task>> = liveDataTaskRepo.getTasks()

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    sealed class Nav : NavigationCommand() {
        object AddNewTask : Nav()
    }
}