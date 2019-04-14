package com.jhughes.todoapp.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T : ViewDataBinding>(val binding: T)
    : RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
    }

    fun markAttach() {
        // Lifecycle.Resource.CREATED doesn't work for this case
        // lifecycleRegistry.markState(Lifecycle.Resource.CREATED)
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        // lifecycleRegistry.markState(Lifecycle.Resource.RESUMED)
    }

    fun markDetach() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}