package com.jhughes.todoapp.injection.component.app

import com.jhughes.todoapp.injection.module.activity.ActivityBindingModule
import com.jhughes.todoapp.injection.module.app.TodoApplicationModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/* extends android injector means dont need explicit void inject(TodoApplication application) method*/

@Singleton
@Component(modules = [
    TodoApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class])
interface TodoApplicationComponent : BaseTodoApplicationComponent {

    @Component.Builder
    interface Builder : BaseTodoApplicationComponent.Builder
}
