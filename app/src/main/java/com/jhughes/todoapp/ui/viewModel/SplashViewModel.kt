package com.jhughes.todoapp.ui.viewModel

import android.util.Log
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.util.SyncScheduler
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ArchViewModel() {

    fun startMain() {
        SyncScheduler.postDelayedToMainThread(1500) {
            Log.d("Navigate", "called navigator")
            navigate(Nav.FinishSplash)
        }
        SyncScheduler.runAllScheduledPostDelayedTasks()
    }

    class Nav {
        object FinishSplash : NavigationRequest()
    }
}