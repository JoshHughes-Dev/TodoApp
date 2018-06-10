package com.jhughes.todoapp.ui

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jhughes.todoapp.data.Navigator
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.factory.SplashViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory

    private var hasPerformedTransition = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SplashViewModel::class.java)

        binding.viewModel = viewModel

        setContentView(binding.root)

        viewModel.navigationEvent.observe(this, Observer<Navigator>({ navigator ->
            handleNavigationEvent(navigator)
        }))

        lifecycle.addObserver(binding.viewModel as LifecycleObserver)
    }

    override fun onStop() {
        super.onStop()
        if (hasPerformedTransition) {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(binding.viewModel as LifecycleObserver)
    }

    private fun handleNavigationEvent(navigator: Navigator?) {

        if (navigator == null) {
            return
        }

        if (navigator.startActivity != null) {
            val intent = Intent(this, navigator.startActivity)
            if (navigator.bundle != null) {
                intent.putExtras(navigator.bundle)
            }
            if (navigator.requestCode > -1) {
                startActivityForResult(intent, navigator.requestCode)
            } else {
                startActivity(intent)
            }
        } else if (navigator.key != null) {
            handleNavigationKeyEvent(navigator)
        }

        if (navigator.isFinishActivity) {
            //normally finish but here do something different
            hasPerformedTransition = true
        }
    }

    private fun handleNavigationKeyEvent(navigator: Navigator) {
        if (navigator.key == SplashViewModel.KEY_OPEN_MAIN) {
            onOpenMain()
        }
    }

    private fun onOpenMain() {
        Log.d("Navigate", "openMainMenu")

        val intent = MainActivity.getStartIntent(this)
        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, splash_icon, "splash")

        startActivity(intent, options.toBundle())
    }
}
