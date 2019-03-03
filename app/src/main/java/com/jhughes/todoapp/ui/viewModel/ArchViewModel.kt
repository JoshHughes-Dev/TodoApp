package com.jhughes.todoapp.ui.viewModel

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.jhughes.todoapp.ui.viewModel.util.*


abstract class ArchViewModel : ObservableViewModel(), ScreenNavigationListener,
        NavigationRequester, LoaderRequester, DialogRequester {

    override val navRequestEventData = EventLiveData<NavigationRequest>()
    override val loadingEventData = EventLiveData<LoadingEvent>()
    override val pendingDialogEvent =  EventLiveData<AlertDialog.Builder>()
    override val pendingDisplayableErrorEvent = EventLiveData<DialogRequester.ErrorWrapper>()

    val delegateAction = EventLiveData<Any>()

    override fun onScreenResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    // dont override this, use new navigation instead
    override fun onRequestClose(): Boolean = false
}