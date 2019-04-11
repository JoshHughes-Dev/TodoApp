package com.jhughes.todoapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class MainActivity : BaseActivity(), AddTaskDialogFragment.OnActionListener,
        TaskAdapter.OnActionListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val tasksAdapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        viewModel = viewModelProvider(factory)
        bindToViewModelObservables(viewModel)

        binding.viewModel = viewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recyclerTasks.apply {
            adapter = tasksAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            //removes fade animation on single row that changes when live data update
        }

        viewModel.tasks.observe(this, Observer {
            Log.d("MainActivity", "tasks updated")
            tasksAdapter.addTasks(it)
        })

        tasksAdapter.onActionListener = this
    }

    override fun handleNavigationRequest(request: NavigationRequest): Boolean {
        return when(request) {
            is MainViewModel.Nav.AddNewTask -> consume {
                val dialog = AddTaskDialogFragment.create()
                dialog.show(supportFragmentManager, AddTaskDialogFragment.TAG)
            }
            else -> super.handleNavigationRequest(request)
        }
    }

    override fun onTaskAdded() {
        viewModel.refreshData()
    }

    override fun onCompleteTask(taskId: Int) {
        //viewModel.taskRepository.completeTask(taskId)
        viewModel.liveDataTaskRepo.completeTask(taskId)
    }

    override fun onActivateTask(taskId: Int) {
        //viewModel.taskRepository.activateTask(taskId)
        viewModel.liveDataTaskRepo.activateTask(taskId)
    }

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
