package com.jhughes.todoapp.ui.viewModel.util

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner

interface DialogRequester {

    val pendingDialogEvent: EventLiveData<AlertDialog.Builder>
    val pendingDisplayableErrorEvent: EventLiveData<ErrorWrapper>

    fun showDialog(builder : AlertDialog.Builder) {
        pendingDialogEvent.value = Event(builder)
    }

    fun showError(error: Throwable, retryCallback: (() -> Unit)? = null) {
        pendingDisplayableErrorEvent.value = Event(ErrorWrapper(error, retryCallback))
    }

    data class ErrorWrapper(val error: Throwable, val retryCallback: (() -> Unit)?)
}

interface DialogHandler {
    fun bindToDialogObservable(lifecycleOwner: LifecycleOwner, dialogRequester: DialogRequester) {
        dialogRequester.pendingDialogEvent.observe(lifecycleOwner, EventObserver {
            handleDialogBuilder(it)
        })
        dialogRequester.pendingDisplayableErrorEvent.observe(lifecycleOwner, EventObserver {
            handleDisplayableError(it.error, it.retryCallback)
        })
    }

    fun handleDialogBuilder(builder: AlertDialog.Builder)

    fun handleDisplayableError(error: Throwable, retryCallback: (() -> Unit)?)
}