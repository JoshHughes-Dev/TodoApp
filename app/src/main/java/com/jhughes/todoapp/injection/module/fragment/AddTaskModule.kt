package com.jhughes.todoapp.injection.module.fragment

import android.app.Application
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.injection.scope.FragmentScope
import com.jhughes.todoapp.injection.scopedItems.FragmentItem
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AddTaskModule {

    @Provides
    @FragmentScope
    fun provideFragmentItem() : FragmentItem{
        return FragmentItem()
    }

    @Provides
    @FragmentScope
    fun provideAddTaskViewModelFactory(
            application: Application, taskRepository: TaskRepository): AddTaskViewModelFactory {
        return AddTaskViewModelFactory(application, taskRepository)
    }
}