package com.jhughes.todoapp.ui.viewModel.task

import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import javax.inject.Inject

class SimpleTaskDetailsViewModel @Inject constructor(
        private val taskRepository: TaskRepository) : TaskDetailsViewModel() {

    override val task: MutableLiveData<Task> = MutableLiveData()

    override fun setTaskId(taskId: Int) {
        taskRepository.getTask(taskId) {
            task.value = it
        }
    }

    override fun deleteTask() {
        task.value?.let {
            taskRepository.deleteTask(it)
        }
        navigate(Nav.CloseAndRefresh)
    }

    sealed class Nav : NavigationCommand() {
        object CloseAndRefresh : Nav()
    }
}