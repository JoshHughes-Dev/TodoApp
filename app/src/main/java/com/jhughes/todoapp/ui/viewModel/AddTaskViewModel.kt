package com.jhughes.todoapp.ui.viewModel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.PaperDbTaskRepo
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class AddTaskViewModel @Inject constructor(
        private val taskRepository: TaskRepository,
        private val paperDbTaskRepo: PaperDbTaskRepo) : ArchViewModel() {

    val descriptionText : MutableLiveData<String> = MutableLiveData()

    @Bindable
    fun getError() : Boolean {
        return descriptionText.value.isNullOrBlank()
        //todo bind error
    }

    fun save() {

        val value = descriptionText.value

        if (value == null || value.isNullOrBlank()) {
            notifyPropertyChanged(BR.error)
        } else {
//            taskRepository.addTask(value) {
//                navigate(Nav.AddedTask)
//            }
            paperDbTaskRepo.addTask(value) {
                navigate(Nav.AddedTask)
            }
        }
    }

    class Nav {
        object AddedTask : NavigationRequest()
    }
}