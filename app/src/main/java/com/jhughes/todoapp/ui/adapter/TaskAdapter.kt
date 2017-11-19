package com.jhughes.todoapp.ui.adapter

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.databinding.RowTodoItemBinding
import com.jhughes.todoapp.ui.viewModel.TaskRowViewModel
import org.joda.time.DateTime

class TaskAdapter(private val application : Application) : RecyclerView.Adapter<DataBindingViewHolder<RowTodoItemBinding>>() {

    private val tasks: MutableList<Task> = ArrayList()

    init {
        tasks.add(Task(true, "task 1", DateTime.now()))
        tasks.add(Task(false, "task 2", DateTime.now()))
        tasks.add(Task(false, "task 3", DateTime.now()))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBindingViewHolder<RowTodoItemBinding> {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = RowTodoItemBinding.inflate(inflater, parent, false)

        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<RowTodoItemBinding>?, position: Int) {
        holder!!.binding.viewModel = TaskRowViewModel(application, tasks[position])
        holder.binding.executePendingBindings()
    }
}