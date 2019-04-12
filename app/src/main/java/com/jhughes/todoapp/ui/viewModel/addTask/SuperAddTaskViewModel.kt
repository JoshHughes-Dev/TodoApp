package com.jhughes.todoapp.ui.viewModel.addTask

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.jhughes.todoapp.data.SimpleResult
import com.jhughes.todoapp.data.domain.repo.SuperTasksRepo
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuperAddTaskViewModel @Inject constructor(
        private val superTasksRepo: SuperTasksRepo) : AddTaskViewModel() {

    @Bindable
    fun getError(): Boolean {
        return descriptionText.value.isNullOrBlank()
        //todo bind error
    }

    override fun save() {
        with(descriptionText.value) {
            if (this == null || isNullOrBlank()) {
                notifyPropertyChanged(BR.error)
            } else {
                viewModelScope.launch {
                    showLoader()
                    val result = withContext(Dispatchers.Default) {
                       superTasksRepo.addTask(this@with)
                    }
                    dismissLoader()
                    when(result) {
                        is SimpleResult.Success -> {
                            navigate(NavigationRequest.Close)
                        }
                        is SimpleResult.Error -> {
                            showError(result.exception)
                        }
                    }
                }
            }
        }
    }
}