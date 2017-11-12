package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.os.Handler
import javax.inject.Inject

class SplashViewModel @Inject internal constructor(
        application: Application,
        private val uiThread: Handler) : BaseViewModel(application) {

    var navigator : Navigator? = null

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