package com.jhughes.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.databinding.RowTaskItemBinding
import com.jhughes.todoapp.ui.viewModel.tasks.TaskRowViewModel
import com.jhughes.todoapp.ui.viewModel.util.EventObserver

class TaskAdapter : DataBindingAdapter<RowTaskItemBinding>() {

    var onActionListener: OnActionListener? = null

    private val tasks: MutableList<Task> = ArrayList()

    fun addTasks(tasks: List<Task>) {
        val diffCallback = TaskDiffCallback(this.tasks, tasks)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.tasks.clear()
        this.tasks.addAll(tasks)

        diffResult.dispatchUpdatesTo(this)
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

class TaskDiffCallback(private val oldTasks : List<Task>,
                       private val newTasks : List<Task>) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTasks[oldItemPosition] == newTasks[newItemPosition]
    }

    override fun getOldListSize(): Int = oldTasks.size

    override fun getNewListSize(): Int = newTasks.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTasks[oldItemPosition] == newTasks[newItemPosition]
    }
}