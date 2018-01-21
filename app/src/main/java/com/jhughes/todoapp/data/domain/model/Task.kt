package com.jhughes.todoapp.data.domain.model

import org.joda.time.DateTime

data class Task(
        var id : Int,
        var isComplete: Boolean,
        var description: String,
        var createdAt: DateTime
)