package com.jhughes.todoapp.ui.util

import com.jhughes.todoapp.consume
import com.jhughes.todoapp.ui.viewModel.util.NavigationRequest
import com.jhughes.todoapp.ui.viewModel.util.Router

class UiControllerRouter(private val uiController: UiController) : Router() {
    override fun routeNavigationRequest(request: NavigationRequest): Boolean {
        return when (request) {
            is NavigationRequest.Close -> consume {
                uiController.close()
            }
            is NavigationRequest.Back -> consume {
                uiController.back()
            }
            else -> false
        }
    }
}