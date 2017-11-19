package com.jhughes.todoapp.ui.adapter

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.databinding.RowTaskItemBinding
import com.jhughes.todoapp.ui.viewModel.TaskRowViewModel

class TaskAdapter(private val application : Application) : RecyclerView.Adapter<DataBindingViewHolder<RowTaskItemBinding>>() {

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
        holder!!.binding.viewModel = TaskRowViewModel(application, tasks[position])
        holder.binding.executePendingBindings()
    }
}