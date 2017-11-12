package com.jhughes.todoapp.ui

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.injection.component.DaggerSplashActivityComponent
import com.jhughes.todoapp.injection.component.SplashActivityComponent
import com.jhughes.todoapp.injection.module.SplashActivityModule
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity : AppCompatActivity()  {

    private lateinit var component : SplashActivityComponent
    private lateinit var binding : ActivitySplashBinding
    private lateinit var viewModel : SplashViewModel

    @Inject lateinit var viewModelFactory: SplashViewModelFactory

    private var hasPerformedTransistion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpInjection();

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

    private fun onOpenMain() {
        Log.d("Navigate", "openMainMenu")

        val intent = MainActivity.getStartIntent(this);
        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, splash_icon, "splash")

        hasPerformedTransistion = true
        startActivity(intent, options.toBundle())
    }

    override fun onStop() {
        super.onStop()
        if(hasPerformedTransistion) {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(binding.viewModel as LifecycleObserver)
    }
}
