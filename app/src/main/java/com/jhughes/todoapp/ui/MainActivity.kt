package com.jhughes.todoapp.ui

import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.injection.component.DaggerMainActivityComponent
import com.jhughes.todoapp.injection.component.MainActivityComponent
import com.jhughes.todoapp.injection.module.MainActivityModule
import com.jhughes.todoapp.ui.viewModel.MainViewModel
import com.jhughes.todoapp.ui.viewModel.factory.MainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var component : MainActivityComponent
    private lateinit var binding : ActivityMainBinding

    @Inject lateinit var context : Context
    @Inject lateinit var connectivityManager: ConnectivityManager
    @Inject lateinit var progressDialog: ProgressDialog
    @Inject lateinit var viewModelFactory: MainViewModelFactory

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivity", "onCreate")

        val todoApp = application as TodoApplication

        component = DaggerMainActivityComponent.builder()
                .mainActivityModule(MainActivityModule(this))
                .applicationComponent(todoApp.component)
                .build()

        component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}
