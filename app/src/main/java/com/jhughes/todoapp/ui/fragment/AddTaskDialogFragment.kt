package com.jhughes.todoapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.jhughes.todoapp.R
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.ui.viewModel.util.viewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddTaskDialogFragment : AppCompatDialogFragment() {

    private lateinit var binding : FragmentAddTaskBinding

    @Inject lateinit var factory: ViewModelProvider.Factory

    private lateinit var onActionListener : OnActionListener

    companion object Factory {
        val TAG = "AddTaskDialogFragment"

        fun create() : AddTaskDialogFragment {

            val args = Bundle()

            val dialog = AddTaskDialogFragment()
            dialog.arguments = args
            return dialog
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        binding.viewModel = viewModelProvider(factory)

//        binding.viewModel?.dismissEvent?.observe(this, Observer<Void>{
//            onActionListener.onTaskAdded()
//            dismiss()
//        })

        initToolbar(binding.toolbar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun initToolbar(toolbar : Toolbar) {

        toolbar.navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_close)

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

    interface OnActionListener {
        fun onTaskAdded()
    }
}