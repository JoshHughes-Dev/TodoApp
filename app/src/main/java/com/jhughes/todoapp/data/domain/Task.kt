package com.jhughes.todoapp.data.domain

import org.joda.time.DateTime

data class Task(
        var id : Int,
        val isComplete: Boolean,
        val description: String,
        val createdAt: DateTime
)