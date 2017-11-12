package com.jhughes.todoapp.injection.component

import com.jhughes.todoapp.injection.module.SplashActivityModule
import com.jhughes.todoapp.injection.scope.PerActivity
import com.jhughes.todoapp.ui.SplashActivity
import dagger.Component

@PerActivity
@Component(
        modules = arrayOf(SplashActivityModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)
interface SplashActivityComponent {

    fun inject(activity: SplashActivity)
}