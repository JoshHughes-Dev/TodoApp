package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import javax.inject.Inject

class AddTaskViewModel @Inject constructor(
        application: Application,
        private val taskRepository: TaskRepository) : ArchViewModel() {

    val descriptionText : ObservableField<String> = ObservableField()


    @Bindable
    fun getError() : Boolean {
        return descriptionText.get().isNullOrBlank()
        //todo bind error
    }

    fun save() {

        if(descriptionText.get().isNullOrBlank()) {
            notifyPropertyChanged(BR.error)
        } else {
            taskRepository.addTask(descriptionText.get(), TaskRepository.GetTaskCallback {

            })
        }
    }
}