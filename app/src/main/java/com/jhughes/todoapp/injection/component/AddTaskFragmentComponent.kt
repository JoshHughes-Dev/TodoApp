package com.jhughes.todoapp.injection.component

import com.jhughes.todoapp.injection.module.AddTaskFragmentModule
import com.jhughes.todoapp.injection.scope.PerFragment
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import dagger.Component

@PerFragment
@Component(
        modules = arrayOf(AddTaskFragmentModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)
interface AddTaskFragmentComponent {

    fun inject(fragment: AddTaskDialogFragment)
}