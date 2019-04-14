package com.jhughes.todoapp.ui.viewModel.addTask

import androidx.databinding.Bindable
import com.jhughes.todoapp.BR
import com.jhughes.todoapp.data.domain.repo.LiveDataTaskRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class LiveDataAddTaskViewModel @Inject constructor(
        private val liveDataTaskRepo: LiveDataTaskRepo) : AddTaskViewModel() {

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
            liveDataTaskRepo.addTask(value) {
                navigate(NavigationRequest.Close)
            }
        }
    }

}