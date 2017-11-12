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

    val splashCompleteEvent = SingleLiveEvent<Void>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMain() {
        uiThread.postDelayed({
            Log.d("Navigate", "called navigator")
            splashCompleteEvent.call()
        }, 1500)
    }

}