package com.jhughes.todoapp.ui.viewModel.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jhughes.todoapp.data.SimpleResult
import com.jhughes.todoapp.data.State
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.SuperTasksRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuperTasksViewModel @Inject constructor(
        private val superTasksRepo: SuperTasksRepo) : TasksViewModel() {

    val tasks: LiveData<List<Task>> = Transformations.map(superTasksRepo.getTasks(viewModelScope)) { state ->
        when(state) {
            is State.Loading -> {
                showLoader()
                Log.d("SuperTasksViewModel", "tasks state : Loading")
                emptyList()
            }
            is State.Success -> {
                dismissLoader()
                Log.d("SuperTasksViewModel", "tasks state : Success")
                state.data
            }
            is State.Error -> {
                dismissLoader()
                showError(state.exception)
                Log.d("SuperTasksViewModel", "tasks state : Error")
                emptyList()
            }
        }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                superTasksRepo.completeTask(taskId)
            }
            when(result) {
                is SimpleResult.Success -> {
                    //do nothing
                }
                is SimpleResult.Error -> {
                    showError(result.exception)
                }
            }
        }
    }

    fun activateTask(taskId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                superTasksRepo.activateTask(taskId)
            }
            when(result) {
                is SimpleResult.Success -> {
                    //do nothing
                }
                is SimpleResult.Error -> {
                    showError(result.exception)
                }
            }
        }
    }

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    class Nav {
        object AddNewTask : NavigationRequest()
    }
}