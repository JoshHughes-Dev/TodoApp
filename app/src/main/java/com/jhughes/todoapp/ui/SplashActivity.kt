package com.jhughes.todoapp.ui

import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    @Inject lateinit var factory: ViewModelProvider.Factory

    private var hasPerformedTransition = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        viewModel = viewModelProvider(factory)
        bindToViewModelObservables(viewModel)

        binding.viewModel = viewModel

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        viewModel.startMain()
    }

    override fun onStop() {
        super.onStop()
//        if (hasPerformedTransition) {
//            finish()
//        }
    }

    override fun handleNavigationRequest(request: NavigationRequest): Boolean {
        return when(request) {
            is SplashViewModel.Nav.FinishSplash -> consume {
                val intent = MainActivity.getStartIntent(this)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, splash_icon, "splash")

                startActivity(intent, options.toBundle())
                //hasPerformedTransition = true
                finish()
            }
            else -> super.handleNavigationRequest(request)
        }
    }
}
