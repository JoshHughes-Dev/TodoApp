package com.jhughes.todoapp.ui.viewModel

import androidx.lifecycle.LiveData
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.LiveDataTaskRepo
import com.jhughes.todoapp.data.domain.repo.PaperDbTaskRepo
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject


class MainViewModel @Inject constructor(
        val taskRepository: TaskRepository,
        val liveDataTaskRepo: LiveDataTaskRepo,
        val paperDbTaskRepo: PaperDbTaskRepo) : ArchViewModel() {

    //val tasks : MutableLiveData<List<Task>> = MutableLiveData()

    //val tasks : LiveData<List<Task>> = liveDataTaskRepo.getTasks()

    val tasks : LiveData<List<Task>> = paperDbTaskRepo.getTasks()

    init {
        //refreshData()
    }

    fun fabClick() {
        navigate(Nav.AddNewTask)
    }

    fun refreshData() {
//        taskRepository.getTasks {
//            this.tasks.value = it
//        }
    }

    class Nav {
        object AddNewTask : NavigationRequest()
    }
}