package com.jhughes.todoapp.data.local.mapper

import com.jhughes.todoapp.data.domain.Task
import com.jhughes.todoapp.data.local.entity.RoomTask

class TaskMapper {

    fun toLocal(task : Task) : RoomTask {
       return RoomTask(task.id, task.isComplete, task.description, task.createdAt)
    }

    fun toDomain(roomTask: RoomTask) : Task {
        return Task(roomTask.id, roomTask.isComplete, roomTask.description, roomTask.createdAt)
    }
}