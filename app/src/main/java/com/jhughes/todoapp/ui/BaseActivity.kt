package com.jhughes.todoapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

    override val routers: MutableList<Router> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        routers.apply {
            add(createCustomRouter())
            add(UiControllerRouter(this@BaseActivity))
        }
    }

    override fun handleLoadingEvent(loadingEvent: LoadingEvent) {
        when (loadingEvent) {
            is LoadingEvent.Show -> {
            }
            //dialogHandler.showLoading(loadingEvent.cancelable, loadingEvent.cancelCallback)
            is LoadingEvent.Hide -> {
            }
            //dialogHandler.hideLoading()
            is LoadingEvent.Complete -> {
            }
//                TaskCompletedDialog.show(this, loadingEvent.message).apply {
//                    setOnDismissListener { loadingEvent.callback?.invoke() }
//                }
        }
    }

    override fun handleDialogBuilder(builder: AlertDialog.Builder) {
        //dialogHandler.showDialog(builder)
    }

    override fun handleDisplayableError(error: Throwable, retryCallback: (() -> Unit)?) {
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

    override fun close() {
        finish()
    }

    override fun back() {
        onBackPressed()
    }

    fun bindToViewModelObservables(viewModel: ArchViewModel) {
        bindToNavigationObservable(this, viewModel)
        bindToLoaderObservable(this, viewModel)
        bindToDialogObservable(this, viewModel)
    }

    open fun handleNavigationRequest(request: NavigationRequest): Boolean {
        return false
    }

    private fun createCustomRouter(): Router {
        return object : Router() {
            override fun routeNavigationRequest(request: NavigationRequest) = handleNavigationRequest(request)
        }
    }


}