package com.jhughes.todoapp.ui.viewModel.tasks

import com.jhughes.todoapp.ui.viewModel.CoroutineViewModel

abstract class TasksViewModel : CoroutineViewModel() {

    abstract fun fabClick()
}