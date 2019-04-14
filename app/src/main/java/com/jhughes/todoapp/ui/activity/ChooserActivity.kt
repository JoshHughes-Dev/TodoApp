package com.jhughes.todoapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.ActivityChooserBinding
import com.jhughes.todoapp.ui.activity.tasks.*
import com.jhughes.todoapp.ui.viewModel.ChooserViewModel
import com.jhughes.todoapp.ui.viewModel.util.Router
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class ChooserActivity : BaseActivity() {

    private lateinit var binding : ActivityChooserBinding
    private lateinit var viewModel : ChooserViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooserBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        viewModel = viewModelProvider(factory)
        bindToViewModelObservables(viewModel)

        binding.viewModel = viewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        navigationRouters.add(activityRouter())
    }

    private fun activityRouter() = Router { navCommand ->
        when(navCommand) {
            is ChooserViewModel.Nav.SimpleExample -> consume {
                startActivity(SimpleTasksActivity.getStartIntent(requireActivity()))
            }
            is ChooserViewModel.Nav.LiveDataExample -> consume {
                startActivity(LiveDataTasksActivity.getStartIntent(requireActivity()))
            }
            is ChooserViewModel.Nav.PaperDbExample -> consume {
                startActivity(PaperDbTasksActivity.getStartIntent(requireActivity()))
            }
            is ChooserViewModel.Nav.CoroutinesExample -> consume {
                startActivity(CoroutinesTasksActivity.getStartIntent(requireActivity()))
            }
            is ChooserViewModel.Nav.SuperExample -> consume {
                startActivity(SuperTasksActivity.getStartIntent(requireActivity()))
            }
            else -> false
        }
    }

    companion object Factory {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ChooserActivity::class.java)
        }
    }
}