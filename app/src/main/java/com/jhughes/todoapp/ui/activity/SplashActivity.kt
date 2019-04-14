package com.jhughes.todoapp.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivitySplashBinding
import com.jhughes.todoapp.ui.viewModel.SplashViewModel
import com.jhughes.todoapp.ui.viewModel.util.Router
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationRouters.add(activityRouter())

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

    private fun activityRouter() = Router { navCommand ->
        when (navCommand) {
            is SplashViewModel.Nav.FinishSplash -> consume {
                val intent = ChooserActivity.getStartIntent(this)
                startActivity(intent)
                finish()
            }
            else -> false
        }
    }
}
