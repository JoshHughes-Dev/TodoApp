package com.jhughes.todoapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class MainActivity : BaseActivity(), AddTaskDialogFragment.OnActionListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject lateinit var factory: ViewModelProvider.Factory

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = viewModelProvider(factory)

        binding.viewModel = viewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

//        viewModel.navigationEvent.observe(this, Observer {
//            openAddTask()
//        })
    }

    override fun onTaskAdded() {
        viewModel.setTasks()
    }

    private fun openAddTask() {
        val dialog = AddTaskDialogFragment.create()
        dialog.show(supportFragmentManager, AddTaskDialogFragment.TAG)
    }
}
