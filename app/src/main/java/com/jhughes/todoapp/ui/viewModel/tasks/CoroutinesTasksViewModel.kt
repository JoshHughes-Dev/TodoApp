package com.jhughes.todoapp.ui.viewModel.tasks

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.CoroutineTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesTasksViewModel @Inject constructor(
        val coroutineTaskRepo: CoroutineTaskRepo) : TasksViewModel() {

    //val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    val tasks: LiveData<List<Task>> = coroutineTaskRepo.getLiveTasks(viewModelScope)

    init {
        refreshData()
    }

    override fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    fun refreshData() {
        //viewModelScope.launch {
        //    showLoader()
       //     val result = coroutineTaskRepo.getTasks()
       //     dismissLoader()
       //     processResult(result)
       // }
    }

    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            coroutineTaskRepo.completeTask(taskId)
            //val result = coroutineTaskRepo.getTasks()
            //processResult(result)
        }
    }

    fun activateTask(taskId: Int) {
        viewModelScope.launch {
            coroutineTaskRepo.activateTask(taskId)
           // val result = coroutineTaskRepo.getTasks()
           // processResult(result)
        }
    }

    fun clearTasks() {
        viewModelScope.launch {
            coroutineTaskRepo.clearTasks()
        }

    }

//    private fun processResult(result: Result<List<Task>>) {
//        when (result) {
//            is Result.Success -> {
//                tasks.value = result.data
//            }
//            is Result.Error -> {
//                Log.d("MainViewModlel", "result error")
//            }
//        }
//    }

    class Nav {
        object AddNewTask : NavigationCommand()
    }
}