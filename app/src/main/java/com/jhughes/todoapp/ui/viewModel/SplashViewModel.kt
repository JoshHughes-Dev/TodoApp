package com.jhughes.todoapp.ui.viewModel

import android.util.Log
import com.jhughes.todoapp.data.util.AppExecutors
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val appExecutors: AppExecutors) : ArchViewModel() {

    fun startMain() {
        appExecutors.mainThreadHandler().postDelayed({
            Log.d("Navigate", "called navigator")
            navigate(Nav.FinishSplash)
        }, 1500)
    }

    class Nav {
        object FinishSplash : NavigationRequest()
    }
}