package com.jhughes.todoapp.ui.util

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

interface UiController {

    fun requireContext() : Context

    fun requireActivity() : FragmentActivity

    fun startActivity(requestCode: Int? = null, func: () -> Intent)

    fun showDialog(dialogFragment: DialogFragment, tag: String)

    fun close()

    fun back()
}