package com.jhughes.todoapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.jhughes.todoapp.ui.util.UiController
import com.jhughes.todoapp.ui.util.UiControllerRouter
import com.jhughes.todoapp.ui.viewModel.ArchViewModel
import com.jhughes.todoapp.ui.viewModel.util.*
import dagger.android.support.DaggerDialogFragment

abstract class BaseDialogFragment : DaggerDialogFragment(), NavigationHandler, LoaderHandler,
        DialogHandler, UiController {

    override val routers: MutableList<Router> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routers.apply {
            add(createCustomRouter())
            add(UiControllerRouter(this@BaseDialogFragment))
        }
    }

    override fun handleLoadingEvent(loadingEvent: LoadingEvent) {
        when (loadingEvent) {
            is LoadingEvent.Show -> {}
                //dialogHandler.showLoading(loadingEvent.cancelable, loadingEvent.cancelCallback)
            is LoadingEvent.Hide -> {}
                //dialogHandler.hideLoading()
            is LoadingEvent.Complete -> {}
                //TaskCompletedDialog.show(this.activity, loadingEvent.message).apply {
                //    setOnDismissListener { loadingEvent.callback?.invoke() }
                //}
        }
    }

    override fun handleDialogBuilder(builder: AlertDialog.Builder) {
        //dialogHandler.showDialog(builder)
    }

    override fun handleDisplayableError(error: Throwable, retryCallback: (() -> Unit)?) {
        //dialogHandler.showError(error, retryCallback)
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

    override fun startActivity(requestCode: Int?, func: () -> Intent) {
        if (requestCode != null) {
            startActivityForResult(func(), requestCode)
        } else {
            startActivity(func())
        }
    }

    override fun showDialog(dialogFragment: DialogFragment, tag: String) {
        dialogFragment.show(requireFragmentManager(), tag)
    }

    override fun close() {
        try {
            dismiss()
        } catch (e: Exception) {
            activity?.onBackPressed()
        }
    }

    override fun back() {
        dismiss()
    }

    companion object {
        const val EXTRA_THEME = "extra_theme"
    }
}