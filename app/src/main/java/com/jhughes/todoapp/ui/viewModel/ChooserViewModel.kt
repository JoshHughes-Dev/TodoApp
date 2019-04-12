package com.jhughes.todoapp.ui.viewModel

import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import javax.inject.Inject

class ChooserViewModel @Inject constructor(): ArchViewModel() {

    fun showSimpleExample() {
        navigate(Nav.SimpleExample)
    }

    fun showLiveDataExample() {
        navigate(Nav.LiveDataExample)
    }

    fun showPaperDbExample() {
        navigate(Nav.PaperDbExample)
    }

    fun showCoroutinesExample() {
        navigate(Nav.CoroutinesExample)
    }

    fun showSuperExample() {
        navigate(Nav.SuperExample)
    }

    class Nav {
        object SimpleExample : NavigationRequest()
        object LiveDataExample : NavigationRequest()
        object PaperDbExample : NavigationRequest()
        object CoroutinesExample : NavigationRequest()
        object SuperExample : NavigationRequest()
    }
}