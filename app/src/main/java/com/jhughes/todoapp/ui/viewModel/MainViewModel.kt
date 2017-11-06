package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.ObservableField
import android.widget.Toast
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application) : BaseViewModel(application) {

    val testText = ObservableField("")

    init {
        testText.set("Hi my name is josh")
    }

    fun fabClick() {
        Toast.makeText(context, "fab clicked", Toast.LENGTH_LONG).show()
        testText.set("ahhhh ya clicked it")
    }
}