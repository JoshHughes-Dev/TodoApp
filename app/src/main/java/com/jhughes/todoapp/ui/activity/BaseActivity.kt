package com.jhughes.todoapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.jhughes.todoapp.ui.util.UiController
import com.jhughes.todoapp.ui.util.UiControllerRouter
import com.jhughes.todoapp.ui.viewModel.ArchViewModel
import com.jhughes.todoapp.ui.viewModel.util.*
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity(), UiController,
        NavigationHandler, LoaderHandler, DialogHandler {

    override val navigationRouters: MutableList<Router> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationRouters.add(UiControllerRouter(this@BaseActivity))
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationRouters.clear()
    }

    override fun onLoadingEvent(loadingEvent: LoadingEvent) {
        when (loadingEvent) {
            is LoadingEvent.Show -> {
                Log.d("BaseActivity", "show loader")
            }
            is LoadingEvent.Hide -> {
                Log.d("BaseActivity", "hide loader")
            }
            is LoadingEvent.Complete -> {
                Log.d("BaseActivity", "task complete")
            }
        }
    }

    override fun onDialogToDisplay(builder: AlertDialog.Builder) {
        //dialogHandler.showDialog(builder)
    }

    override fun onErrorToDisplay(error: Throwable, retryCallback: (() -> Unit)?) {
        //dialogHandler.showError(error, retryCallback)
    }

    override fun requireContext(): Context = this

    override fun requireActivity(): FragmentActivity = this

    override fun startActivity(requestCode: Int?, func: () -> Intent) {
        if (requestCode != null) {
            startActivityForResult(func(), requestCode)
        } else {
            startActivity(func())
        }
    }

    override fun showDialog(dialogFragment: DialogFragment, tag: String) {
        dialogFragment.show(supportFragmentManager, tag)
    }

    override fun close() = finish()

    override fun back() = onBackPressed()

    fun bindToViewModelObservables(viewModel: ArchViewModel) {
        observeNavigationCommandsFrom(viewModel)
        observeLoadingEventsFrom(viewModel)
        observeDialogRequestsFrom(viewModel)
    }
}