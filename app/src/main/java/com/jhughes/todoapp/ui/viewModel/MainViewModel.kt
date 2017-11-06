package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.databinding.Bindable
import android.databinding.ObservableField
import android.widget.Toast
import com.jhughes.todoapp.BR
import javax.inject.Inject


class MainViewModel @Inject internal constructor(
        application: Application) : BaseViewModel(application) {

    val testText = ObservableField("")
    var hasclicked = false

    init {
        testText.set("Hi my name is Josh")
    }

    @Bindable
    fun getTestTextTwo(): CharSequence {
        return if(hasclicked) "yay its so cool" else "i made an app"
    }

    fun fabClick() {
        Toast.makeText(context, "fab clicked", Toast.LENGTH_LONG).show()
        hasclicked = true;
        testText.set("ahhhh ya clicked it")
        notifyPropertyChanged(BR.testTextTwo)
    }
}