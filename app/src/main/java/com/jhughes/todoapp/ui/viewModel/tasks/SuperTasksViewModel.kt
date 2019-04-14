package com.jhughes.todoapp.ui.viewModel.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jhughes.todoapp.data.Resource
import com.jhughes.todoapp.data.SimpleResult
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.SuperTasksRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import kotlinx.coroutines.launch
import javax.inject.Inject

class SuperTasksViewModel @Inject constructor(
        private val superTasksRepo: SuperTasksRepo) : TasksViewModel() {

    val tasks: LiveData<List<Task>> = Transformations.map(superTasksRepo.getTasks(viewModelScope)) { state ->
        when(state) {
            is Resource.Loading -> {
                showLoader()
                Log.d("SuperTasksViewModel", "tasks state : Loading")
                emptyList()
            }
            is Resource.Success -> {
                dismissLoader()
                Log.d("SuperTasksViewModel", "tasks state : Success")
                state.data
            }
            is Resource.Error -> {
                dismissLoader()
                showError(state.exception)
                Log.d("SuperTasksViewModel", "tasks state : Error")
                emptyList()
            }
        }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            when(val result = superTasksRepo.completeTask(taskId)) {
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
            when(val result = superTasksRepo.activateTask(taskId)) {
                is SimpleResult.Success -> {
                    //do nothing
                }
                is SimpleResult.Error -> {
                    showError(result.exception)
                }
            }
        }
    }

    fun clearTasks() {
        viewModelScope.launch {
            superTasksRepo.clearTasks()
        }
    }

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    class Nav {
        object AddNewTask : NavigationCommand()
    }
}