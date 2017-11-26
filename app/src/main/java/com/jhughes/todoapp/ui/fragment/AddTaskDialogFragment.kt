package com.jhughes.todoapp.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.*
import com.jhughes.todoapp.R
import com.jhughes.todoapp.databinding.FragmentAddTaskBinding
import com.jhughes.todoapp.injection.component.AddTaskFragmentComponent
import com.jhughes.todoapp.injection.component.DaggerAddTaskFragmentComponent
import com.jhughes.todoapp.injection.module.AddTaskFragmentModule
import com.jhughes.todoapp.ui.MainActivity
import com.jhughes.todoapp.ui.viewModel.AddTaskViewModel
import com.jhughes.todoapp.ui.viewModel.factory.AddTaskViewModelFactory
import javax.inject.Inject

class AddTaskDialogFragment : DialogFragment() {

    private lateinit var component : AddTaskFragmentComponent
    private lateinit var binding : FragmentAddTaskBinding

    @Inject lateinit var viewModelFactory: AddTaskViewModelFactory

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
                .mainActivityComponent((activity as MainActivity).component)
                .build()

        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater!!, container, false)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddTaskViewModel::class.java)

        val closeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_close)

        binding.toolbar.navigationIcon = closeDrawable

        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }

        binding.toolbar.inflateMenu(R.menu.menu_add_task)
        binding.toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                val id = item!!.itemId

                if(id == R.id.action_save) {
                    //todo onTaskAdded callback
                    dismiss()
                    return true
                }

                return false
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_add_task, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId

        if(id == R.id.action_save) {
            //todo onTaskAdded
            dismiss()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}