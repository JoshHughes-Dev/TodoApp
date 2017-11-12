package com.jhughes.todoapp.ui

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.view.Window
import com.jhughes.todoapp.R
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.injection.component.DaggerSplashActivityComponent
import com.jhughes.todoapp.injection.component.SplashActivityComponent
import com.jhughes.todoapp.injection.module.SplashActivityModule
import com.jhughes.todoapp.ui.viewModel.Navigator
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import javax.inject.Inject


class SplashActivity : AppCompatActivity(), Navigator  {

    private lateinit var component : SplashActivityComponent
    private lateinit var binding : ActivitySplashBinding

    @Inject lateinit var viewModelFactory: SplashViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoApp = application as TodoApplication

        component = DaggerSplashActivityComponent.builder()
                .splashActivityModule(SplashActivityModule(this))
                .applicationComponent(todoApp.component)
                .build()

        component.inject(this)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SplashViewModel::class.java)

        setContentView(R.layout.activity_splash)

        binding.viewModel?.navigator = this

        lifecycle.addObserver(binding.viewModel as LifecycleObserver)
    }

//    override fun onStart() {
//        super.onStart()
//        binding.viewModel?.startMain()
//    }

    override fun onOpenMain() {
        startActivity(MainActivity.getStartIntent(this))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewModel?.navigator = null

        lifecycle.removeObserver(binding.viewModel as LifecycleObserver)
    }
}
