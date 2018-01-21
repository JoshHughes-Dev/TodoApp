package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.databinding.ObservableField
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.SingleLiveEvent
import com.jhughes.todoapp.data.domain.repo.TaskRepository

class AddTaskViewModel constructor(
        application: Application,
        private val taskRepository: TaskRepository) : BaseViewModel(application) {

    val descriptionText : ObservableField<String> = ObservableField()

    val dismissEvent = SingleLiveEvent<Void>()

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
                dismissEvent.postValue(null)
            })
        }
    }
}