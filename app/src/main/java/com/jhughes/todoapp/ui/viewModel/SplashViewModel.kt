package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Handler
import android.util.Log
import com.jhughes.todoapp.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject internal constructor(
        application: Application,
        private val uiThread: Handler) : BaseViewModel(application), LifecycleObserver {

    val startActivityEvent = SingleLiveEvent<Void>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMain() {
        uiThread.postDelayed({
            Log.d("Navigate", "called navigator")
            startActivityEvent.call()
        }, 1500)
    }

    //todo navigator idea
    //navigator handle observable navigation stuff
    //scoped to viewModel so dont hold reference to view
    //have view subscribe to that.
    //therefore delagate all actions to navigator class who is observed by view
}