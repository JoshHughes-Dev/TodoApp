package com.jhughes.todoapp.ui.viewModel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Handler
import android.util.Log
import com.jhughes.todoapp.SingleLiveEvent
import com.jhughes.todoapp.data.Navigator
import javax.inject.Inject

class SplashViewModel @Inject internal constructor(
        application: Application,
        private val uiThread: Handler) : BaseViewModel(application), LifecycleObserver {

    companion object {
        val KEY_OPEN_MAIN = "key_splash_open_main"
    }

    val navigationEvent = SingleLiveEvent<Navigator>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMain() {
        uiThread.postDelayed({
            Log.d("Navigate", "called navigator")
            splashCompleteEvent()
        }, 1500)
    }

    private fun splashCompleteEvent() {

//        navigationEvent.value = Navigator.newBuilder()
//                .withActivity(MainActivity::class.java)
//                .withFinish()
//                .build()
        navigationEvent.value = Navigator.newBuilder()
                .withKey(KEY_OPEN_MAIN)
                .build()

        navigationEvent.call()
    }

}