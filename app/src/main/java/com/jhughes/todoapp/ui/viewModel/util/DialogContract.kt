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

    fun LifecycleOwner.observeDialogRequestsFrom(dialogRequester: DialogRequester) {
        dialogRequester.pendingDialogEvent.observe(this, EventObserver {
            onDialogToDisplay(it)
        })
        dialogRequester.pendingDisplayableErrorEvent.observe(this, EventObserver {
            onErrorToDisplay(it.error, it.retryCallback)
        })
    }

    fun onDialogToDisplay(builder: AlertDialog.Builder)

    fun onErrorToDisplay(error: Throwable, retryCallback: (() -> Unit)?)
}