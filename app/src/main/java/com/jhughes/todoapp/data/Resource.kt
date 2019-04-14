package com.jhughes.todoapp.data

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    class Success<out T : Any>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
}

