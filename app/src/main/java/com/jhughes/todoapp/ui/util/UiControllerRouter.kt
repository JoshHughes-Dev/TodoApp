package com.jhughes.todoapp.ui.util

import com.jhughes.todoapp.consume
import com.jhughes.todoapp.ui.viewModel.util.NavigationCommand
import com.jhughes.todoapp.ui.viewModel.util.Router

class UiControllerRouter(private val uiController: UiController) : Router({ navCommand ->
    when (navCommand) {
        is NavigationCommand.Close -> consume {
            uiController.close()
        }
        is NavigationCommand.Back -> consume {
            uiController.back()
        }
        else -> false
    }
})

