package com.jhughes.todoapp.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.*
import com.jhughes.todoapp.R
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.injection.component.AddTaskFragmentComponent
import com.jhughes.todoapp.injection.component.DaggerAddTaskFragmentComponent
import com.jhughes.todoapp.injection.module.AddTaskFragmentModule
import com.jhughes.todoapp.ui.viewModel.AddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory
import javax.inject.Inject

class AddTaskDialogFragment : DialogFragment() {

    private lateinit var component : AddTaskFragmentComponent
    private lateinit var binding : FragmentAddTaskBinding

    @Inject lateinit var viewModelFactory: AddTaskViewModelFactory
    @Inject lateinit var taskRepository: TaskRepository

    companion object Factory {
        val TAG = "AddTaskDialogFragment"

        fun create() : AddTaskDialogFragment {

            val args = Bundle()

            val dialog = AddTaskDialogFragment()
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)

        component = DaggerAddTaskFragmentComponent.builder()
                .addTaskFragmentModule(AddTaskFragmentModule(activity))
                .applicationComponent((activity.application as TodoApplication).component)
                .build()

        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater!!, container, false)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddTaskViewModel::class.java)

        binding.viewModel?.dismissEvent?.observe(this, Observer<Void>({
           dismiss()
        }))

        initToolbar(binding.toolbar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun initToolbar(toolbar : Toolbar) {

        toolbar.navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)

        toolbar.setNavigationOnClickListener {
            dismiss()
        }

        toolbar.inflateMenu(R.menu.menu_add_task)

        toolbar.setOnMenuItemClickListener {item : MenuItem? ->
            val id = item!!.itemId

            var result = false

            if(id == R.id.action_save) {
                binding.viewModel?.save()
                result = true
            }

            result
        }
    }
}