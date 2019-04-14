package com.jhughes.todoapp.ui.viewModel.addTask

import androidx.databinding.Bindable
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.CoroutineTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesAddTaskViewModel @Inject constructor(
        private val coroutineTaskRepo: CoroutineTaskRepo) : AddTaskViewModel() {

    @Bindable
    fun getError(): Boolean {
        return descriptionText.value.isNullOrBlank()
        //todo bind error
    }

    override fun save() {
        val value = descriptionText.value

        if (value == null || value.isNullOrBlank()) {
            notifyPropertyChanged(BR.error)
        } else {
            viewModelScope.launch {
                coroutineTaskRepo.addTask(value)
                navigate(Nav.AddedTask)
            }
        }
    }

    class Nav {
        object AddedTask : NavigationRequest()
    }
}