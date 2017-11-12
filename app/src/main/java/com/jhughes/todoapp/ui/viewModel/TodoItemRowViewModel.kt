package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import com.jhughes.todoapp.data.domain.TodoItem

class TodoItemRowViewModel (application: Application, private val todoItem : TodoItem) : BaseViewModel(application) {

    var isComplete = ObservableBoolean(todoItem.isComplete)

    @Bindable
    fun getDescription() : CharSequence {
        return todoItem.description
    }
}