package com.jhughes.todoapp.ui.viewModel.util

import android.content.Intent


interface ScreenNavigationListener {

    fun onScreenResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onRequestClose(): Boolean
}