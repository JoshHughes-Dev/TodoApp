package com.jhughes.todoapp.ui.viewModel.addTask

import androidx.databinding.Bindable
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class SimpleAddTaskViewModel @Inject constructor(
        private val taskRepository: TaskRepository) : AddTaskViewModel() {

    @Bindable
    fun getError() : Boolean {
        return descriptionText.value.isNullOrBlank()
        //todo bind error
    }

    override fun save() {
        val value = descriptionText.value

        if (value == null || value.isNullOrBlank()) {
            notifyPropertyChanged(BR.error)
        } else {
            taskRepository.addTask(value) {
                navigate(Nav.AddedTask)
            }
        }
    }

    class Nav {
        object AddedTask : NavigationRequest()
    }
}