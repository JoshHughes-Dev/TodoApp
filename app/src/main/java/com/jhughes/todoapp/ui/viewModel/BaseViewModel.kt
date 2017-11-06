package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

open class BaseViewModel(application : Application) : AndroidViewModel(application), Observable {

    protected val context : Context
        get() = getApplication()

    private val registry: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(onPropertyChangedCallback: Observable.OnPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback)
    }

    override fun removeOnPropertyChangedCallback(onPropertyChangedCallback: Observable.OnPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback)
    }

    fun notifyChange() {
        registry.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        registry.notifyCallbacks(this, fieldId, null)
    }

}