package com.jhughes.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.databinding.RowTaskItemBinding
import com.jhughes.todoapp.ui.viewModel.TaskRowViewModel
import com.jhughes.todoapp.ui.viewModel.util.EventObserver

class TaskAdapter : DataBindingAdapter<RowTaskItemBinding>() {

    var onActionListener: OnActionListener? = null

    private val tasks: MutableList<Task> = ArrayList()

    fun addTasks(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun addTask(task: Task) {
        this.tasks.add(task)
        notifyItemInserted(tasks.size)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewDataBinding(parent: ViewGroup, viewType: Int): RowTaskItemBinding {
        val inflater = LayoutInflater.from(parent.context)
        return RowTaskItemBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<RowTaskItemBinding>, position: Int) {
        val task = tasks[position]
        val viewModel = TaskRowViewModel(task)

        holder.apply {
            binding.viewModel = viewModel
            viewModel.delegateAction.observe(this, EventObserver {
                when (it) {
                    is TaskRowViewModel.Action.StatusChange -> {
                        if (it.isComplete) {
                            onActionListener?.onCompleteTask(it.taskId)
                        } else {
                            onActionListener?.onActivateTask(it.taskId)
                        }
                    }
                }
            })
        }
    }

    interface OnActionListener {
        fun onCompleteTask(taskId: Int)
        fun onActivateTask(taskId: Int)
    }
}