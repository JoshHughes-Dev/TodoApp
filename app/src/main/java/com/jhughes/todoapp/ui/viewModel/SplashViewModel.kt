package com.jhughes.todoapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.jhughes.todoapp.data.util.AppExecutors
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val appExecutors: AppExecutors) : ArchViewModel(), LifecycleObserver {

    companion object {
        val KEY_OPEN_MAIN = "key_splash_open_main"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startMain() {
        appExecutors.mainThreadHandler().postDelayed({
            Log.d("Navigate", "called navigator")
            splashCompleteEvent()
        }, 1500)
    }

    private fun splashCompleteEvent() {

    }

}