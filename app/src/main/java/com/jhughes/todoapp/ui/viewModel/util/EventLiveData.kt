package com.jhughes.todoapp.ui.viewModel.util

import androidx.lifecycle.MutableLiveData

class EventLiveData<T> : MutableLiveData<Event<T>>() {

    fun peek() : T? {
        return this.value?.peekContent()
    }
}