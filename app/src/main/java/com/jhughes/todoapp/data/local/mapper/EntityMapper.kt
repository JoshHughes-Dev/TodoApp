package com.jhughes.todoapp.data.local.mapper

interface EntityMapper<T, V> {

    fun toEntity(model: T): V

    fun toDomain(entity: V): T

}