package com.jhughes.todoapp.data

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    class Success<out T : Any>(val data: T) : State<T>()
    class Error(val exception: Throwable) : State<Nothing>()
}

