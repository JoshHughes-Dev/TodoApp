package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.ObservableBoolean
import com.jhughes.todoapp.data.domain.model.Task

class TaskRowViewModel(application: Application, private val task: Task) : BaseViewModel(application) {

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