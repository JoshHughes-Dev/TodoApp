package com.jhughes.todoapp.ui.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import javax.inject.Inject

class AddTaskViewModel @Inject constructor(
        private val taskRepository: TaskRepository) : ArchViewModel() {

    val descriptionText : MutableLiveData<String> = MutableLiveData()

    @Bindable
    fun getError() : Boolean {
        return descriptionText.value.isNullOrBlank()
        //todo bind error
    }

    fun save() {

        val value = descriptionText.value

        if (value == null || value.isNullOrBlank()) {
            notifyPropertyChanged(BR.error)
        } else {
            taskRepository.addTask(value) {

            }
        }
    }
}