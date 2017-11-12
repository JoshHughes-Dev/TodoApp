package com.jhughes.todoapp.ui.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBindingViewHolder<T : ViewDataBinding>(val binding: T)
    : RecyclerView.ViewHolder(binding.root)