package com.jhughes.todoapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.injection.scopedItems.ActivityItem
import com.jhughes.todoapp.injection.scopedItems.SingletonItem
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), AddTaskDialogFragment.OnActionListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject lateinit var singletonItem: SingletonItem
    @Inject lateinit var activityItem: ActivityItem

    @Inject lateinit var viewModelFactory: MainViewModelFactory

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        binding.viewModel = viewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel.navigationEvent.observe(this, Observer {
            openAddTask()
        })
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, singletonItem.itemDescription, Toast.LENGTH_LONG).show()
    }

    override fun onTaskAdded() {
        viewModel.setTasks()
    }

    private fun openAddTask() {
        val dialog = AddTaskDialogFragment.create()
        dialog.show(supportFragmentManager, AddTaskDialogFragment.TAG)
    }
}
