package com.jhughes.todoapp.ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingAdapter<T : ViewDataBinding> : RecyclerView.Adapter<DataBindingViewHolder<T>>() {

    abstract fun onCreateViewDataBinding(parent: ViewGroup, viewType: Int) : T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val binding = onCreateViewDataBinding(parent, viewType)

        val viewHolder = DataBindingViewHolder(binding)
        binding.lifecycleOwner = viewHolder

        return viewHolder
    }

    override fun onViewAttachedToWindow(holder: DataBindingViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: DataBindingViewHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }
}