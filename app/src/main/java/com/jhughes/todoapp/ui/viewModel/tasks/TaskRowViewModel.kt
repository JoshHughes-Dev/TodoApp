package com.jhughes.todoapp.ui.viewModel.tasks

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.ui.viewModel.ArchViewModel
import com.jhughes.todoapp.ui.viewModel.util.Event

class TaskRowViewModel(private val task: Task) : ArchViewModel() {

    val isComplete = ObservableBoolean(task.isComplete)

    val description : CharSequence?
    @Bindable
    get() = task.description

    init {
        isComplete.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                task.isComplete = isComplete.get()
                delegateAction.value = Event(Action.StatusChange(task.id, isComplete.get()))
            }
        })
    }

    sealed class Action {
        data class StatusChange(val taskId: Int, val isComplete : Boolean) : Action()
    }
}