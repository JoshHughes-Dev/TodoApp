package com.jhughes.todoapp.injection.component.app

import com.jhughes.todoapp.injection.module.activity.ActivityBindingModule
import com.jhughes.todoapp.injection.module.app.DebugTodoApplicationModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DebugTodoApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class])
interface DebugTodoApplicationComponent : BaseTodoApplicationComponent {

    @Component.Builder
    interface Builder : BaseTodoApplicationComponent.Builder
}