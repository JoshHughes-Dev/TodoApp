package com.jhughes.todoapp.injection.component

import com.jhughes.todoapp.TodoApplication
import com.jhughes.todoapp.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
//AndroidInjectionModule::class,
public interface ApplicationComponent {

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): ApplicationComponent
//    }

    fun inject(app : TodoApplication)
}