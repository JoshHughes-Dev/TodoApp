package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Handler
import javax.inject.Inject

class SplashViewModel @Inject internal constructor(
        application: Application,
        private val uiThread: Handler) : BaseViewModel(application), LifecycleObserver {

    var navigator : Navigator? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMain() {
        uiThread.postDelayed({
            navigator?.onOpenMain()
        }, 1500)
    }

    //todo navigator idea
    //navigator handle observable navigation stuff
    //scoped to viewModel so dont hold reference to view
    //have view subscribe to that.
    //therefore delagate all actions to navigator class who is observed by view
}