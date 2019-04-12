package com.jhughes.todoapp.ui.viewModel.addTask

import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.ui.viewModel.CoroutineViewModel

abstract class AddTaskViewModel : CoroutineViewModel() {

    val descriptionText : MutableLiveData<String> = MutableLiveData()

    abstract fun save()
}