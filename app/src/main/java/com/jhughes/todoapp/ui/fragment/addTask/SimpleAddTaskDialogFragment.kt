package com.jhughes.todoapp.ui.fragment.addTask

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.R
import com.jhughes.todoapp.consume
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.ui.fragment.BaseDialogFragment
import com.jhughes.todoapp.ui.viewModel.addTask.SimpleAddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.util.Router
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import javax.inject.Inject

class SimpleAddTaskDialogFragment : BaseDialogFragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var viewModel: SimpleAddTaskViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var onActionListener: OnActionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onActionListener = context as OnActionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    context.toString() + " must implement " + OnActionListener::class.java.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
        viewModel = viewModelProvider(factory)
        navigationRouters.add(dialogRouter())
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

    private fun dialogRouter() = Router { navCommand ->
        when (navCommand) {
            SimpleAddTaskViewModel.Nav.AddedTask -> consume {
                onActionListener.onTaskAdded()
                dismiss()
            }
            else -> false
        }
    }

    interface OnActionListener {
        fun onTaskAdded()
    }

    companion object Factory {
        const val TAG = "AddTaskDialogFragment"

        fun create(): SimpleAddTaskDialogFragment {

            val args = Bundle()

            val dialog = SimpleAddTaskDialogFragment()
            dialog.arguments = args
            return dialog
        }
    }
}