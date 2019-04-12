package com.jhughes.todoapp.ui.activity.tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivityTasksBinding
import com.jhughes.todoapp.ui.BaseActivity
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import com.jhughes.todoapp.ui.fragment.addTask.SuperAddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.tasks.SuperTasksViewModel
import com.jhughes.todoapp.ui.viewModel.util.LoadingEvent
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class SuperTasksActivity : BaseActivity(), TaskAdapter.OnActionListener {

    private lateinit var binding: ActivityTasksBinding
    private lateinit var viewModel: SuperTasksViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val tasksAdapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTasksBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        viewModel = viewModelProvider(factory)
        bindToViewModelObservables(viewModel)

        binding.viewModel = viewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recyclerTasks.apply {
            adapter = tasksAdapter
            //removes fade animation on single row that changes when live data update
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false // didnt work
        }

        viewModel.tasks.observe(this, Observer {
            Log.d("MainActivity", "tasks updated, count: ${it.size}")
            tasksAdapter.addTasks(it)
        })

        tasksAdapter.onActionListener = this
    }

    override fun handleNavigationRequest(request: NavigationRequest): Boolean {
        return when (request) {
            is SuperTasksViewModel.Nav.AddNewTask -> consume {
                val dialog = SuperAddTaskDialogFragment.create()
                dialog.show(supportFragmentManager, SuperAddTaskDialogFragment.TAG)
            }
            else -> super.handleNavigationRequest(request)
        }
    }

    override fun handleLoadingEvent(loadingEvent: LoadingEvent) {
        super.handleLoadingEvent(loadingEvent)
        when (loadingEvent) {
            is LoadingEvent.Show -> {
                binding.apply {
                    recyclerTasks.visibility = View.GONE
                    loader.visibility = View.VISIBLE
                }
            }
            is LoadingEvent.Hide -> {
                binding.apply {
                    recyclerTasks.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                }
            }
        }
    }

    override fun onCompleteTask(taskId: Int) {
        viewModel.completeTask(taskId)
    }

    override fun onActivateTask(taskId: Int) {
        viewModel.activateTask(taskId)
    }

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SuperTasksActivity::class.java)
        }
    }
}