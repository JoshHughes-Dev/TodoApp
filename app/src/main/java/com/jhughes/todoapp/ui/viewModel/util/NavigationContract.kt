package com.jhughes.todoapp.ui.viewModel.util

import androidx.lifecycle.LifecycleOwner


interface NavigationInvoker {
    val navCommandEventData : EventLiveData<NavigationCommand>

    fun navigate(command : NavigationCommand) {
        navCommandEventData.value = Event(command)
    }
}

open class NavigationCommand {
    object Close : NavigationCommand()
    object Back : NavigationCommand()
}

interface NavigationHandler {

    val navigationRouters : MutableList<Router>

    fun LifecycleOwner.observeNavigationCommandsFrom(navigationInvoker: NavigationInvoker) {
        navigationInvoker.navCommandEventData.observe(this, EventObserver { navCommand ->
            loop@ for(router in navigationRouters) {
                if(router.handler(navCommand)) break@loop
            }
        })
    }
}

open class Router(val handler : (navCommand : NavigationCommand) -> Boolean)