package com.jhughes.todoapp.ui

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.util.Log
import android.view.Window
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.injection.component.DaggerSplashActivityComponent
import com.jhughes.todoapp.injection.component.SplashActivityComponent
import com.jhughes.todoapp.injection.module.SplashActivityModule
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import javax.inject.Inject


class SplashActivity : AppCompatActivity()  {

    private lateinit var component : SplashActivityComponent
    private lateinit var binding : ActivitySplashBinding
    private lateinit var viewModel : SplashViewModel

    @Inject lateinit var viewModelFactory: SplashViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpInjection();

        setUpWindowTransition()

        binding = ActivitySplashBinding.inflate(layoutInflater)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SplashViewModel::class.java)

        binding.viewModel = viewModel

        setContentView(binding.root)

        viewModel.splashCompleteEvent.observe(this, Observer<Void> {
            onOpenMain()
        })

        lifecycle.addObserver(binding.viewModel as LifecycleObserver)
    }

    private fun setUpInjection() {
        val todoApp = application as TodoApplication

        component = DaggerSplashActivityComponent.builder()
                .splashActivityModule(SplashActivityModule(this))
                .applicationComponent(todoApp.component)
                .build()

        component.inject(this)
    }

    private fun setUpWindowTransition() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
    }

    private fun onOpenMain() {
        Log.d("Navigate", "openMainMenu")
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(binding.viewModel as LifecycleObserver)
    }
}
