package com.jhughes.todoapp.ui.viewModel.tasks

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.PaperDbTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import javax.inject.Inject

class PaperDbTasksViewModel @Inject constructor(
        val paperDbTaskRepo: PaperDbTaskRepo) : TasksViewModel() {

    val tasks : LiveData<List<Task>> = paperDbTaskRepo.getTasks()

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    class Nav {
        object AddNewTask : NavigationCommand()
    }
}