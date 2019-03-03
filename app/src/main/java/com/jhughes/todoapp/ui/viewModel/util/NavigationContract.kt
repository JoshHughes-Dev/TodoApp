package com.jhughes.todoapp.ui.viewModel.util

import androidx.lifecycle.LifecycleOwner


interface NavigationRequester {
    val navRequestEventData : EventLiveData<NavigationRequest>

    fun navigate(request : NavigationRequest) {
        navRequestEventData.value = Event(request)
    }
}

open class NavigationRequest {
    object Close : NavigationRequest()
    object Back : NavigationRequest()
}

interface NavigationHandler {

    val routers : MutableList<Router>

    fun bindToNavigationObservable(lifecycleOwner: LifecycleOwner, navigationRequester: NavigationRequester) {
        navigationRequester.navRequestEventData.observe(lifecycleOwner, EventObserver { event ->
            loop@ for(router in routers) {
                if(router.routeNavigationRequest(event)) break@loop
            }
        })
    }
}

abstract class Router {
    abstract fun routeNavigationRequest(request: NavigationRequest) : Boolean
}