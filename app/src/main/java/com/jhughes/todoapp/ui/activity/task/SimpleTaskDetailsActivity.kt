package com.jhughes.todoapp.ui.activity.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.databinding.ActivityTaskDetailsBinding
import com.jhughes.todoapp.ui.activity.BaseActivity
import com.jhughes.todoapp.ui.viewModel.task.SimpleTaskDetailsViewModel
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class SimpleTaskDetailsActivity : BaseActivity() {

    private lateinit var binding : ActivityTaskDetailsBinding
    private lateinit var viewModel : SimpleTaskDetailsViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(factory)
    }

    companion object {
        private const val EXTRA_TASK_ID = "extra_task_id"

        fun getStartIntent(context: Context, taskId: Int): Intent {
            return Intent(context, SimpleTaskDetailsActivity::class.java).apply {
                putExtra(EXTRA_TASK_ID, taskId)
            }
        }
    }
}