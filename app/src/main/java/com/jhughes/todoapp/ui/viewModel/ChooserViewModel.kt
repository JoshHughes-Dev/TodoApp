package com.jhughes.todoapp.ui.viewModel

import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
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

    sealed class Nav : NavigationCommand() {
        object SimpleExample : Nav()
        object LiveDataExample : Nav()
        object PaperDbExample : Nav()
        object CoroutinesExample : Nav()
        object SuperExample : Nav()
    }
}