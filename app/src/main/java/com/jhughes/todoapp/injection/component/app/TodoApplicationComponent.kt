package com.jhughes.todoapp.injection.component.app

import android.app.Application
import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.injection.module.activity.ActivityBindingModule
import com.jhughes.todoapp.injection.module.app.TodoApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/* extends android injector means dont need explicit void inject(TodoApplication application) method*/

@Singleton
@Component(modules = [
    TodoApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class])
interface TodoApplicationComponent : AndroidInjector<TodoApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TodoApplicationComponent.Builder
        fun build(): TodoApplicationComponent
    }
}
