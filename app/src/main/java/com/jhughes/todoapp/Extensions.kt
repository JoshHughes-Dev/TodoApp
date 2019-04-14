package com.jhughes.todoapp

import com.jhughes.todoapp.data.Result
import com.jhughes.todoapp.data.SimpleResult

/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

suspend fun <T : Any> safeActionResult(func : suspend () -> T) : Result<T> {
    return try {
        Result.Success(func())
    } catch (e : Exception) {
        Result.Error(e)
    }
}

suspend fun safeAction(func : suspend () -> Unit) : SimpleResult {
    return try {
        func()
        SimpleResult.Success
    } catch (e : Exception) {
        SimpleResult.Error(e)
    }
}