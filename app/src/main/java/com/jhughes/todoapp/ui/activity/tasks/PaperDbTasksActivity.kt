package com.jhughes.todoapp.ui.activity.tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jhughes.todoapp.R
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivityTasksBinding
import com.jhughes.todoapp.ui.activity.BaseActivity
import com.jhughes.todoapp.ui.adapter.TaskAdapter
import com.jhughes.todoapp.ui.fragment.addTask.PaperDbAddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.tasks.PaperDbTasksViewModel
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class PaperDbTasksActivity : BaseActivity(), TaskAdapter.OnActionListener {

    private lateinit var binding : ActivityTasksBinding
    private lateinit var viewModel: PaperDbTasksViewModel

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
        binding.toolbar.initTasksMenu()

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
        return when(request) {
            is PaperDbTasksViewModel.Nav.AddNewTask -> consume {
                val dialog = PaperDbAddTaskDialogFragment.create()
                dialog.show(supportFragmentManager, PaperDbAddTaskDialogFragment.TAG)
            }
            else -> super.handleNavigationRequest(request)
        }
    }

    override fun onCompleteTask(taskId: Int) {
        viewModel.paperDbTaskRepo.completeTask(taskId)
    }

    override fun onActivateTask(taskId: Int) {
        viewModel.paperDbTaskRepo.activateTask(taskId)
    }

    private fun Toolbar.initTasksMenu() {
        inflateMenu(R.menu.menu_tasks)
        setOnMenuItemClickListener { item ->
            val id = item?.itemId

            var result = false

            if (id == R.id.action_delete_all) {
                viewModel.paperDbTaskRepo.clearTasks()
                result = true
            }

            result
        }
    }

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, PaperDbTasksActivity::class.java)
        }
    }
}
