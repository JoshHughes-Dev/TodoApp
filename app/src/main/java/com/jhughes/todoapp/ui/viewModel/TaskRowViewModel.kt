package com.jhughes.todoapp.ui.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import com.jhughes.todoapp.data.domain.model.Task

class TaskRowViewModel(private val task: Task) : ArchViewModel() {

    val isComplete = ObservableBoolean(task.isComplete)
    var listener : OnActionListener? = null

    init {
        isComplete.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                listener?.onStatusChange(task.id.toString(), isComplete.get())
            }
        })
    }

    @Bindable
    fun getDescription() : CharSequence {
        return task.description
    }

    interface OnActionListener {
        fun onStatusChange(taskId: String, isComplete : Boolean)
    }
}