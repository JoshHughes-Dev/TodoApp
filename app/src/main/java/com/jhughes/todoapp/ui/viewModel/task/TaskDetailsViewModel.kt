package com.jhughes.todoapp.ui.viewModel.task

import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.ui.viewModel.CoroutineViewModel

abstract class TaskDetailsViewModel : CoroutineViewModel() {

    abstract val task : MutableLiveData<Task>

    abstract fun setTaskId(taskId: Int)

    abstract fun deleteTask()
}