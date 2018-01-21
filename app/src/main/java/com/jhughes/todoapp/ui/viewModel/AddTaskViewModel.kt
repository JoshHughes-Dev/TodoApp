package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.ObservableField
import com.jhughes.todoapp.SingleLiveEvent
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import javax.inject.Inject

class AddTaskViewModel @Inject internal constructor(
        application: Application, val taskRepository: TaskRepository) : BaseViewModel(application) {

    val descriptionText : ObservableField<String> = ObservableField()

    val dismissEvent = SingleLiveEvent<Void>()

    fun save() {
        //taskRepository.addTask(null)

        taskRepository.addTask()

        dismissEvent.call()
    }
}