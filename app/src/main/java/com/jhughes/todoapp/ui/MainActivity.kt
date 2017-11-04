package com.jhughes.todoapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jhughes.todoapp.databinding.ActivityMainBinding
import com.jhughes.todoapp.ui.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

    }

}
