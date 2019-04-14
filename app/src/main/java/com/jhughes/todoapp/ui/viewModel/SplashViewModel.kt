package com.jhughes.todoapp.ui.viewModel

import android.util.Log
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
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

    sealed class Nav : NavigationCommand() {
        object FinishSplash : Nav()
    }
}