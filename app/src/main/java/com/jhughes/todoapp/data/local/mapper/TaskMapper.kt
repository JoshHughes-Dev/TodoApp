package com.jhughes.todoapp.data.local.mapper

import com.jhughes.todoapp.data.domain.model.Task
import com.jhughes.todoapp.data.local.entity.TaskEntity

class TaskMapper : EntityMapper<Task, TaskEntity> {

    override fun toEntity(model: Task): TaskEntity {
        return TaskEntity(model.id, model.isComplete, model.description, model.createdAt)
    }

    override fun toDomain(entity: TaskEntity) : Task {
        return Task(entity.id, entity.isComplete, entity.description, entity.createdAt)
    }
}