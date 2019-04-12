package com.jhughes.todoapp.ui.viewModel.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.data.Result
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.CoroutineTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoroutinesTasksViewModel @Inject constructor(
        val coroutineTaskRepo: CoroutineTaskRepo) : TasksViewModel() {

    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        refreshData()
    }

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    fun refreshData() {
        viewModelScope.launch {
            showLoader()
            val result = withContext(Dispatchers.Default) {
                coroutineTaskRepo.getTasks()
            }
            dismissLoader()
            processResult(result)
        }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                coroutineTaskRepo.completeTask(taskId)
                coroutineTaskRepo.getTasks()
            }
            processResult(result)
        }
    }

    fun activateTask(taskId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                coroutineTaskRepo.activateTask(taskId)
                coroutineTaskRepo.getTasks()
            }
            processResult(result)
        }
    }

    private fun processResult(result: Result<List<Task>>) {
        when (result) {
            is Result.Success -> {
                tasks.value = result.data
            }
            is Result.Error -> {
                Log.d("MainViewModlel", "result error")
            }
        }
    }

    class Nav {
        object AddNewTask : NavigationRequest()
    }
}