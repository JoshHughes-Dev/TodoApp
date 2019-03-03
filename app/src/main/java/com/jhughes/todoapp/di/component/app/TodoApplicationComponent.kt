package com.jhughes.todoapp.di.component.app

import com.jhughes.todoapp.di.module.activity.ActivityBindingModule
import com.jhughes.todoapp.di.module.app.TodoApplicationModule
import com.jhughes.todoapp.di.module.app.ViewModelFactoryModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TodoApplicationModule::class,
    ActivityBindingModule::class,
    ViewModelFactoryModule::class])
interface TodoApplicationComponent : BaseTodoApplicationComponent {

    @Component.Builder
    interface Builder : BaseTodoApplicationComponent.Builder
}
