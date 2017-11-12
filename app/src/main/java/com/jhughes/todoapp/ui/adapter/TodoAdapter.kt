package com.jhughes.todoapp.ui.adapter

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jhughes.todoapp.data.domain.TodoItem
import com.jhughes.todoapp.databinding.RowTodoItemBinding
import com.jhughes.todoapp.ui.viewModel.TodoItemRowViewModel
import org.joda.time.DateTime

class TodoAdapter(private val application : Application) : RecyclerView.Adapter<DataBindingViewHolder<RowTodoItemBinding>>() {

    private val todoItems : MutableList<TodoItem> = ArrayList()

    init {
        todoItems.add(TodoItem(true, "task 1", DateTime.now()))
        todoItems.add(TodoItem(false, "task 2", DateTime.now()))
        todoItems.add(TodoItem(false, "task 3", DateTime.now()))
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBindingViewHolder<RowTodoItemBinding> {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = RowTodoItemBinding.inflate(inflater, parent, false)

        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<RowTodoItemBinding>?, position: Int) {
        holder!!.binding.viewModel = TodoItemRowViewModel(application, todoItems[position])
        holder.binding.executePendingBindings()
    }
}