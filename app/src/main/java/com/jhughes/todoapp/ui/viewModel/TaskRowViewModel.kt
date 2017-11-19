package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import com.jhughes.todoapp.data.domain.model.Task

class TaskRowViewModel(application: Application, private val task: Task) : BaseViewModel(application) {

    var isComplete = ObservableBoolean(task.isComplete)

    @Bindable
    fun getDescription() : CharSequence {
        return task.description
    }
}