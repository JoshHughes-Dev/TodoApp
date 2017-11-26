package com.jhughes.todoapp.ui.adapter

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.domain.repo.TaskRepository
import com.jhughes.todoapp.databinding.RowTaskItemBinding
import com.jhughes.todoapp.ui.viewModel.TaskRowViewModel

class TaskAdapter(private val application : Application, private val taskRepository: TaskRepository)
    : RecyclerView.Adapter<DataBindingViewHolder<RowTaskItemBinding>>(),
        TaskRowViewModel.OnActionListener {

    private val tasks: MutableList<Task> = ArrayList()

    fun addTasks(tasks : List<Task>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun addTask(task : Task) {
        this.tasks.add(task)
        notifyItemInserted(tasks.size)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBindingViewHolder<RowTaskItemBinding> {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = RowTaskItemBinding.inflate(inflater, parent, false)

        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<RowTaskItemBinding>?, position: Int) {
        var task = tasks[position]
        val viewModel = TaskRowViewModel(application, task)

        viewModel.listener = this

        holder?.binding?.viewModel = viewModel
        holder?.binding?.executePendingBindings()
    }

    override fun onViewDetachedFromWindow(holder: DataBindingViewHolder<RowTaskItemBinding>?) {
        super.onViewDetachedFromWindow(holder)

        holder?.binding?.viewModel?.listener = null
    }

    override fun onStatusChange(taskId: String, isComplete: Boolean) {
        if(isComplete) {
            taskRepository.completeTask(taskId)
        } else {
            taskRepository.activateTask(taskId)
        }
    }
}