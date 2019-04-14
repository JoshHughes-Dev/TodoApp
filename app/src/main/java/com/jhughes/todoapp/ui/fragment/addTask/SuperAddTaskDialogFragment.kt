package com.jhughes.todoapp.ui.fragment.addTask

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.R
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.ui.fragment.BaseDialogFragment
import com.jhughes.todoapp.ui.viewModel.addTask.SuperAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class SuperAddTaskDialogFragment : BaseDialogFragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var viewModel: SuperAddTaskViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        viewModel = viewModelProvider(factory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        bindToViewModelObservables(viewModel)

        initToolbar(binding.toolbar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun onStart() {
        super.onStart()
        binding.txtDescription.requestFocus()
    }

    private fun initToolbar(toolbar: Toolbar) {

        toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)

        toolbar.setNavigationOnClickListener {
            dismiss()
        }

        toolbar.inflateMenu(R.menu.menu_add_task)

        toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            val id = item?.itemId

            var result = false

            if (id == R.id.action_save) {
                binding.viewModel?.save()
                result = true
            }

            result
        }
    }

    companion object Factory {
        const val TAG = "AddTaskDialogFragment"

        fun create(): SuperAddTaskDialogFragment {

            val args = Bundle()

            val dialog = SuperAddTaskDialogFragment()
            dialog.arguments = args
            return dialog
        }
    }
}