package com.jhughes.todoapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.ui.fragment.AddTaskDialogFragment
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddTaskDialogFragment.OnActionListener, HasSupportFragmentInjector {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    //@Inject lateinit var context : Context
    @Inject
    lateinit var connectivityManager: ConnectivityManager
    //@Inject
    //lateinit var progressDialog: ProgressDialog
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
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

    override fun onTaskAdded() {
        viewModel.setTasks()
    }

    private fun openAddTask() {
        val dialog = AddTaskDialogFragment.create()
        dialog.show(supportFragmentManager, AddTaskDialogFragment.TAG)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }
}
