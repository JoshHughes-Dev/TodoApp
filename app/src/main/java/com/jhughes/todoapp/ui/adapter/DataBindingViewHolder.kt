package com.jhughes.todoapp.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T : ViewDataBinding>(val binding: T)
    : RecyclerView.ViewHolder(binding.root)