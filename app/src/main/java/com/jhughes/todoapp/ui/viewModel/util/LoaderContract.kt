package com.jhughes.todoapp.ui.viewModel.util

import androidx.lifecycle.LifecycleOwner

interface LoaderRequester {

    val loadingEventData: EventLiveData<LoadingEvent>

    fun showLoader(cancelable: Boolean = false, cancelCallback: (() -> Unit)? = null) {
        loadingEventData.value = Event(LoadingEvent.Show(cancelable, cancelCallback))
    }

    fun dismissLoader() {
        loadingEventData.value = Event(LoadingEvent.Hide)
    }

    fun showTaskComplete(message: String? = null, callback : (() -> Unit)? = null) {
        loadingEventData.value = Event(LoadingEvent.Complete(message, callback))
    }
}

sealed class LoadingEvent {
    data class Show(val cancelable: Boolean = false, val cancelCallback: (() -> Unit)? = null) : LoadingEvent()
    object Hide : LoadingEvent()
    data class Complete(val message: String?, val callback: (() -> Unit)?) : LoadingEvent()
}

interface LoaderHandler {
    fun LifecycleOwner.observeLoadingEventsFrom(loaderRequester: LoaderRequester) {
        loaderRequester.loadingEventData.observe(this, EventObserver {
            onLoadingEvent(it)
        })
    }

    fun onLoadingEvent(loadingEvent: LoadingEvent)
}