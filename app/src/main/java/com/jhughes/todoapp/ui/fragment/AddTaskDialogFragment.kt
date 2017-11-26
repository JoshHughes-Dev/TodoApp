package com.jhughes.todoapp.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.injection.component.AddTaskFragmentComponent
import com.jhughes.todoapp.injection.component.DaggerAddTaskFragmentComponent
import com.jhughes.todoapp.injection.module.AddTaskFragmentModule
import com.jhughes.todoapp.ui.MainActivity
import com.jhughes.todoapp.ui.viewModel.AddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory
import javax.inject.Inject

class AddTaskDialogFragment : DialogFragment() {

    private lateinit var component : AddTaskFragmentComponent
    private lateinit var binding : FragmentAddTaskBinding

    @Inject lateinit var viewModelFactory: AddTaskViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerAddTaskFragmentComponent.builder()
                .addTaskFragmentModule(AddTaskFragmentModule(activity))
                .mainActivityComponent((activity as MainActivity).component)
                .build()

        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater!!, container, false)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddTaskViewModel::class.java)

        return binding.root
    }
}